package fr.univ_amu.iut.bonus9;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Test du bonus 9 : le compteur de caractères (propriété dérivée partagée par plusieurs vues) se
 * vérifie sans interface graphique.
 */
class NoteTerrainViewModelTest {

  // @Disabled("Retire cette annotation pour activer le test")
  @Test
  void au_demarrage_le_compteur_indique_zero_caractere() {
    NoteTerrainViewModel vm = new NoteTerrainViewModel();

    assertThat(vm.nombreCaracteresProperty().get()).isEqualTo("0 caractères");
  }

  // @Disabled("Retire cette annotation pour activer le test")
  @Test
  void le_compteur_suit_la_longueur_de_la_note() {
    NoteTerrainViewModel vm = new NoteTerrainViewModel();

    vm.noteProperty().set("Pipistrelle");

    assertThat(vm.nombreCaracteresProperty().get())
        .as("11 caractères dans \"Pipistrelle\"")
        .isEqualTo("11 caractères");
  }
}
