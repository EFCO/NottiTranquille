package it.ispw.efco.nottitranquille.view;

import it.ispw.efco.nottitranquille.controller.AccessFX;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Access Boundary.
 * <br>
 * This Use Case shows the process by which User can access to the application.
 *
 * @see it.ispw.efco.nottitranquille.controller.Access
 * @see it.ispw.efco.nottitranquille.model.Role
 * @see it.ispw.efco.nottitranquille.model.Person
 */
public class UserAccessForm extends Application {

    /**
     * Default constructor
     */
    public UserAccessForm() {
    }

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/accessForm.fxml"));

        Parent root = (Parent) loader.load();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setTitle("Notti Tranquille");
        stage.show();

        // Centers to screen
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);

        AccessFX controller = loader.getController();
        controller.setMainStage(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
