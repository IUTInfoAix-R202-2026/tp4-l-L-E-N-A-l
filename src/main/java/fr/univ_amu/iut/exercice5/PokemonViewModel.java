package fr.univ_amu.iut.exercice5;

import com.google.inject.Inject;
import java.util.Optional;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * ViewModel de l'exercice 5 : exposer une COLLECTION observable.
 *
 * <p>Une {@link ObservableList} est à {@code List} ce qu'une {@code StringProperty} est à {@code
 * String} : une version observable. Quand on l'expose et qu'une {@code TableView} s'y abonne, toute
 * modification de la liste (ajout, suppression) se reflète automatiquement dans le tableau, sans le
 * moindre {@code refresh()}.
 *
 * <p>L'utilisateur saisit un nom dans {@code recherche} ; la commande {@code ajouter} demande au
 * {@link PokemonService} (injecté) le Pokémon correspondant et l'ajoute à la liste. Le service joue
 * le rôle de source de données : c'est exactement ce que fera un DAO au TP5.
 */
public class PokemonViewModel {

  private final PokemonService service;

  private final ObservableList<Pokemon> pokemons = FXCollections.observableArrayList();
  private final StringProperty resume = new SimpleStringProperty();
  private final StringProperty recherche = new SimpleStringProperty("");
  private final StringProperty statut = new SimpleStringProperty("");

  @Inject
  public PokemonViewModel(PokemonService service) {

    this.service = service;

    pokemons.setAll(service.tousLesPokemons());

    resume.bind(Bindings.size(pokemons).asString().concat(" Pokémon"));
  }

  public ObservableList<Pokemon> pokemonsProperty() {
    return pokemons;
  }

  public ReadOnlyStringProperty resumeProperty() {
    return resume;
  }

  public StringProperty rechercheProperty() {
    return recherche;
  }

  public ReadOnlyStringProperty statutProperty() {
    return statut;
  }

  /**
   * Ajoute à la liste le Pokémon dont le nom est saisi dans {@code recherche}. La {@code TableView}
   * abonnée se mettra à jour toute seule.
   */
  public void ajouter() {

    Optional<Pokemon> recherchePokemOptional = service.chercherParNom(recherche.get());

    recherchePokemOptional.ifPresentOrElse(
        (value) -> {
          if (!pokemons.contains(value)) {

            pokemons.add(value);
            recherche.setValue("");
            statut.setValue("Pokemon ajouté à la liste.");

          } else {

            statut.setValue("Pokemon déjà présent dans la liste.");
          }
        },
        () -> {
          statut.setValue("introuvable");
        });
  }
}
