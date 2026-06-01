package fr.univ_amu.iut.exercice2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Contrôleur de vue de l'exercice 2.
 *
 * <p>Encore une fois, le contrôleur est un simple câblage : il lie le label au {@code message} du
 * ViewModel, et fait suivre les clics de boutons vers les commandes correspondantes. Aucune logique
 * ici : le "quoi faire" vit dans le ViewModel, le "comment l'afficher" dans le FXML.
 */
public class CompteurController {

  private final CompteurViewModel viewModel;

  @FXML private Label labelCompteur;

  public CompteurController(CompteurViewModel viewModel) {
    this.viewModel = viewModel;
  }

  @FXML
  private void initialize() {

    labelCompteur.textProperty().bind(viewModel.messageProperty());
  }

  @FXML
  private void surIncrementer() {

    viewModel.incrementerCommand();
  }

  @FXML
  private void surDecrementer() {

    viewModel.decrementerCommand();
  }

  @FXML
  private void surReinitialiser() {

    viewModel.reinitialiserCommand();
  }
}
