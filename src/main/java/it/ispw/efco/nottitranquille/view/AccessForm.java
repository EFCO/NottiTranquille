package it.ispw.efco.nottitranquille.view;

import it.ispw.efco.nottitranquille.controller.AccessFX;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AccessForm extends Application {

    /**
     * Default constructor
     */
    public AccessForm() {
    }

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/accessForm.fxml"));

        Parent root = (Parent) loader.load();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();

        AccessFX controller = loader.getController();
        controller.setMainStage(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
