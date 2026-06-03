package fr.univ_amu.iut.bonus8;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Test du bonus 8 : la transformation en majuscules (logique de présentation) se vérifie sans
 * interface graphique ni Canvas.
 */
class MemeViewModelTest {

  // @Disabled("Retire cette annotation pour activer le test")
  @Test
  void le_texte_du_haut_est_affiche_en_majuscules() {
    MemeViewModel vm = new MemeViewModel();

    vm.texteHautProperty().set("quand le PR detecte une pipistrelle");

    assertThat(vm.texteHautAfficheProperty().get())
        .isEqualTo("QUAND LE PR DETECTE UNE PIPISTRELLE");
  }

  // @Disabled("Retire cette annotation pour activer le test")
  @Test
  void le_texte_du_bas_est_affiche_en_majuscules() {
    MemeViewModel vm = new MemeViewModel();

    vm.texteBasProperty().set("a 3h du matin");

    assertThat(vm.texteBasAfficheProperty().get()).isEqualTo("A 3H DU MATIN");
  }

  // @Disabled("Retire cette annotation pour activer le test")
  @Test
  void les_textes_affiches_suivent_les_saisies_en_temps_reel() {
    MemeViewModel vm = new MemeViewModel();

    assertThat(vm.texteHautAfficheProperty().get()).isEmpty();

    vm.texteHautProperty().set("noctule");
    assertThat(vm.texteHautAfficheProperty().get()).isEqualTo("NOCTULE");
  }
}
