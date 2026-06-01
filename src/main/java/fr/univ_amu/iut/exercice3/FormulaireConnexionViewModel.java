package fr.univ_amu.iut.exercice3;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * ViewModel de l'exercice 3 : le formulaire de connexion de VigieChiro PR Companion.
 *
 * <p>Cet exercice rassemble plusieurs apports du module :
 *
 * <ul>
 *   <li><b>validation réactive</b> : le formulaire n'est "validable" que si les deux champs sont
 *       remplis. La propriété {@code validable} se recalcule à chaque frappe (affordance, Nielsen
 *       #5 : on empêche l'erreur en désactivant le bouton) ;
 *   <li>le pattern <b>Command</b> avec gestion d'erreur : {@code connecterCommand} ne laisse jamais
 *       remonter d'exception vers l'interface ; il publie l'état via {@code statut} ;
 *   <li>la <b>dépendance à une interface</b> ({@link ServiceAuth}) plutôt qu'à une implémentation :
 *       c'est ce découplage qui rend le ViewModel testable (avec un faux service) et que Guice
 *       automatisera à l'exercice 4.
 * </ul>
 */
public class FormulaireConnexionViewModel {

  private final ServiceAuth serviceAuth;

  private final StringProperty identifiant = new SimpleStringProperty("");
  private final StringProperty motDePasse = new SimpleStringProperty("");
  private final StringProperty statut = new SimpleStringProperty("");
  private final BooleanProperty validable = new SimpleBooleanProperty(false);

  public FormulaireConnexionViewModel(ServiceAuth serviceAuth) {
    this.serviceAuth = serviceAuth;

    BooleanBinding idNotEmpty = identifiant.isNotEmpty();

    BooleanBinding mdpNotEmpty = motDePasse.isNotEmpty();

    validable.bind(idNotEmpty.and(mdpNotEmpty));
  }

  public StringProperty identifiantProperty() {
    return identifiant;
  }

  public StringProperty motDePasseProperty() {
    return motDePasse;
  }

  public StringProperty statutProperty() {
    return statut;
  }

  public BooleanProperty validableProperty() {
    return validable;
  }

  /**
   * Commande de connexion. Met à jour {@code statut} selon le résultat. Ne lève jamais d'exception
   * vers l'appelant : c'est l'interface qui doit rester maîtresse de l'affichage.
   */
  public void connecterCommand() {

    statut.setValue("Connexion en cours...");

    if (serviceAuth.connecter(identifiant.getValue(), motDePasse.getValue())) {

      statut.setValue("Bienvenue " + identifiantProperty().getValue() + " !");

    } else {

      statut.setValue("Identifiants incorrects. Vérifiez votre saisie.");
    }
  }
}
