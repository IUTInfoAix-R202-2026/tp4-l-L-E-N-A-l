package fr.univ_amu.iut.bonus9;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * ViewModel du bonus 9 : une note de terrain VigieChiro.
 *
 * <p>Ce bonus illustre un bénéfice clef du MVVM : un <b>même</b> ViewModel peut alimenter
 * <b>plusieurs vues</b> simultanément. Ici, une seule propriété {@code note} est éditée dans une
 * zone de saisie, prévisualisée en direct ailleurs, et comptée (nombre de caractères) dans une
 * troisième zone. Tout se synchronise automatiquement parce que les trois widgets se lient au même
 * ViewModel.
 */
public class NoteTerrainViewModel {

  private final StringProperty note = new SimpleStringProperty("");
  private final StringProperty nombreCaracteres = new SimpleStringProperty();

  public NoteTerrainViewModel() {

    nombreCaracteres.bind(Bindings.concat(note.length(), " caractères"));
  }

  public StringProperty noteProperty() {
    return note;
  }

  public ReadOnlyStringProperty nombreCaracteresProperty() {
    return nombreCaracteres;
  }
}
