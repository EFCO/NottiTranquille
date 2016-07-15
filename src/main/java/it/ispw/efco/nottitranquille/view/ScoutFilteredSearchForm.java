package it.ispw.efco.nottitranquille.view;

import it.ispw.efco.nottitranquille.controller.FilteredSearchFX;
import it.ispw.efco.nottitranquille.model.Person;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public class ScoutFilteredSearchForm extends LoggedApplication  {

    public ScoutFilteredSearchForm() {
    }

    @Override
    public void start(Stage stage) throws Exception {
        super.start(stage);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/scoutFilteredSearchForm.fxml"));

        Parent root = (Parent) loader.load();
        stage.setScene(new Scene(root));
        stage.show();

        FilteredSearchFX controller = loader.getController();
        controller.setMainStage(stage);

        controller.setStatusMenuButton();
    }

}