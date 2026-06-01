package fr.univ_amu.iut.exercice2;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * ViewModel de l'exercice 2.
 *
 * <p>Cet exercice illustre deux idées clefs du MVVM :
 *
 * <ol>
 *   <li>une propriété d'AFFICHAGE dérivée ({@code message}) : le ViewModel transforme la donnée
 *       brute du modèle (un entier) en texte prêt à afficher ;
 *   <li>le pattern <b>Command</b> : chaque action de l'interface (clic sur un bouton) correspond à
 *       une méthode publique sans paramètre du ViewModel ({@code incrementerCommand}, etc.).
 * </ol>
 *
 * <p>Le ViewModel reste testable sans interface : voir {@code CompteurViewModelTest}.
 */
public class CompteurViewModel {

  private final Compteur compteur;

  private final StringProperty message = new SimpleStringProperty();

  public CompteurViewModel(Compteur compteur) {

    this.compteur = compteur;

    message.bind(Bindings.concat("Compteur à ", compteur.valeurProperty()));
  }
  ;

  public StringProperty messageProperty() {
    return message;
  }

  // ----- Commandes (pattern Command) -----

  public void incrementerCommand() {

    this.compteur.incrementer();
  }

  public void decrementerCommand() {

    this.compteur.decrementer();
  }

  public void reinitialiserCommand() {

    this.compteur.reinitialiser();
  }
}
