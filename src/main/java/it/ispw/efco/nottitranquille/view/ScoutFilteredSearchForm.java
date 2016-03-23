package it.ispw.efco.nottitranquille.view;

import it.ispw.efco.nottitranquille.controller.FilteredSearch;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.*;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public class ScoutFilteredSearchForm extends Application{

    /**
     * Default constructor
     */
    public ScoutFilteredSearchForm() {
    }

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/fxml/ScoutFilteredSearchForm.fxml"));

        Parent root = (Parent) loader.load();
        stage.setScene(new Scene(root));
        stage.show();

        FilteredSearch controller = loader.getController();
//        controller.setStatusMenuButton();
    }

    public static void main(String[] args) {
        launch(args);
    }


}