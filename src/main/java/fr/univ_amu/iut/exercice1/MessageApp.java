package fr.univ_amu.iut.exercice1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Point d'entrée de l'exercice 1.
 *
 * <p>Cette classe "bootstrappe" l'application MVVM : elle assemble les trois couches dans le bon
 * ordre (Modèle -> ViewModel -> Vue), puis affiche la fenêtre.
 *
 * <p>Notez le {@code setControllerFactory} : c'est lui qui dit à {@link FXMLLoader} comment
 * construire le contrôleur. Ici on le fait <b>à la main</b> en passant le ViewModel déjà prêt. À
 * l'exercice 4, cette ligne sera remplacée par {@code injector::getInstance} et Guice se chargera
 * de tout câbler automatiquement.
 */
public class MessageApp extends Application {

  @Override
  public void start(Stage stage) throws Exception {
    // 1. Couche Modèle.
    Message modele = new Message("Bonjour MVVM");

    // 2. Couche ViewModel (dépend du modèle).
    MessageViewModel viewModel = new MessageViewModel(modele);

    // 3. Couche Vue : on charge le FXML et on fournit le contrôleur déjà
    // relié au ViewModel via le controllerFactory.
    FXMLLoader loader = new FXMLLoader(getClass().getResource("MessageView.fxml"));
    loader.setControllerFactory(type -> new MessageController(viewModel));
    Parent racine = loader.load();

    stage.setTitle("Exercice 1 - Hello MVVM");
    stage.setScene(new Scene(racine));
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
