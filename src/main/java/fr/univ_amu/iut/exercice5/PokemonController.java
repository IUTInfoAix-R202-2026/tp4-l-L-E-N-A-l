package fr.univ_amu.iut.exercice5;

import com.google.inject.Inject;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * Contrôleur de vue de l'exercice 5.
 *
 * <p>Le contrôleur abonne la {@code TableView} à la liste observable du ViewModel, explique à
 * chaque colonne quelle donnée afficher (cell value factory), et câble le formulaire d'ajout (champ
 * de recherche, bouton, statut). Comme {@link Pokemon} est un {@code record}, on lit ses champs via
 * ses accesseurs ({@code numero()}, {@code nom()}, {@code type()}) dans un petit lambda.
 */
public class PokemonController {

  @Inject private PokemonViewModel viewModel;

  @FXML private TableView<Pokemon> table;
  @FXML private TableColumn<Pokemon, String> colNumero;
  @FXML private TableColumn<Pokemon, String> colNom;
  @FXML private TableColumn<Pokemon, String> colType;
  @FXML private Label labelResume;
  @FXML private TextField champRecherche;
  @FXML private Button boutonAjouter;
  @FXML private Label labelStatut;

  @FXML
  private void initialize() {

    colNumero.setCellValueFactory(
        c -> new SimpleStringProperty(String.valueOf(c.getValue().numero())));
    colNom.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().nom()));
    colType.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().type()));

    table.setItems(viewModel.pokemonsProperty());

    labelResume.textProperty().bind(viewModel.resumeProperty());

    champRecherche.textProperty().bindBidirectional(viewModel.rechercheProperty());

    labelStatut.textProperty().bind(viewModel.statutProperty());

    boutonAjouter.disableProperty().bind(viewModel.rechercheProperty().isEmpty());
  }

  @FXML
  private void surAjouter() {

    viewModel.ajouter();
  }
}
