package it.ispw.efco.nottitranquille.view;

import it.ispw.efco.nottitranquille.controller.AccessControllerFX;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Federico on 28/06/2016.
 */
public class AccessForm extends Application {

    /**
     * Default constructor
     */

    public AccessForm() {
    }

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/fxml/AccessForm.fxml"));

        Parent root = (Parent) loader.load();
        stage.setScene(new Scene(root));
        stage.show();

        AccessControllerFX controller = loader.getController();
        controller.setMainStage(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }


}
