package fr.univ_amu.iut.exercice7;

import com.google.inject.Inject;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

/**
 * ViewModel du capstone : vérifier une nuit d'enregistrement (parcours P3 de la SAÉ 2.01).
 *
 * <p>C'est la pierre angulaire du TP : tout ce que vous avez appris s'y combine.
 *
 * <ul>
 *   <li>le service de données est <b>injecté par Guice</b> ({@code @Inject}) ;
 *   <li>la liste des séquences est une <b>ObservableList</b> exposée à la TableView ;
 *   <li>les libellés affichés sont des <b>propriétés dérivées</b> (description de la sélection,
 *       libellé du verdict) ;
 *   <li>les actions sont des <b>commandes</b> ({@code ecouterCommand}, {@code
 *       enregistrerVerdictCommand}).
 * </ul>
 *
 * <p>Tout est testable sans interface : voir {@code QualificationViewModelTest}.
 */
public class QualificationViewModel {

  private static final DateTimeFormatter HEURE = DateTimeFormatter.ofPattern("HH:mm");

  private final NuitVerification nuit;

  private final ObjectProperty<Sequence> sequenceSelectionnee = new SimpleObjectProperty<>();
  private final BooleanBinding peutEcouter = sequenceSelectionnee.isNotNull();
  private final StringProperty descriptionSelection = new SimpleStringProperty();
  private final StringProperty verdictSaisi = new SimpleStringProperty("");
  private final StringProperty verdictGlobalLibelle = new SimpleStringProperty();

  @Inject
  public QualificationViewModel(ServiceNuits service) {

    this.nuit = service.chargerNuit();

    descriptionSelection.bind(
        Bindings.createStringBinding(
            () ->
                peutEcouterProperty().get()
                    ? "Séquence "
                        + sequenceSelectionnee.get().getHorodatage().format(HEURE)
                        + " - "
                        + String.format(
                            "%.1f", sequenceSelectionnee.get().getFrequenceDominanteKHz())
                        + "kHz"
                    : "(sélectionnez une séquence dans le tableau)",
            sequenceSelectionnee));

    verdictGlobalLibelle.bind(
        Bindings.createStringBinding(
            () ->
                nuit.getVerdictGlobal() == null || nuit.getVerdictGlobal().isEmpty()
                    ? "Verdict global : (à saisir)"
                    : "Verdict global : " + nuit.getVerdictGlobal(),
            nuit.verdictGlobalProperty()));
  }

  public ObservableList<Sequence> sequencesProperty() {
    return nuit.getSequences();
  }

  public ObjectProperty<Sequence> sequenceSelectionneeProperty() {
    return sequenceSelectionnee;
  }

  public BooleanBinding peutEcouterProperty() {
    return peutEcouter;
  }

  public ReadOnlyStringProperty descriptionSelectionProperty() {
    return descriptionSelection;
  }

  public StringProperty commentaireProperty() {
    return nuit.commentaireProperty();
  }

  public StringProperty verdictSaisiProperty() {
    return verdictSaisi;
  }

  public ReadOnlyStringProperty verdictGlobalLibelleProperty() {
    return verdictGlobalLibelle;
  }

  /** Les trois verdicts possibles, proposés dans la ChoiceBox. */
  public List<String> listeVerdicts() {
    return List.of("OK", "Douteux", "À jeter");
  }

  /** Marque la séquence sélectionnée comme "Écoutée". */
  public void ecouterCommand() {

    sequenceSelectionnee.get().setStatut("Écoutée");
  }

  /** Enregistre le verdict saisi dans le modèle de la nuit. */
  public void enregistrerVerdictCommand() {

    nuit.setVerdictGlobal(verdictSaisi.getValue());
  }
}
