package it.ispw.efco.nottitranquille.view;

import it.ispw.efco.nottitranquille.SingletonEmployee;
import it.ispw.efco.nottitranquille.controller.MainFX;
import it.ispw.efco.nottitranquille.model.Designer;
import it.ispw.efco.nottitranquille.model.Person;
import it.ispw.efco.nottitranquille.model.Scout;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.ArrayList;

public class MainScreen extends LoggedApplication {

    /**
     * Default constructor
     */
    public MainScreen() {
    }

    @Override
    public void start(Stage stage) throws Exception {
        super.start(stage);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/mainScreen.fxml"));

        Parent root = (Parent) loader.load();
        stage.setScene(new Scene(root));
        stage.show();

        MainFX controller = loader.getController();
        controller.setMainStage(stage);

        controller.getEmployeeFirstName().setText(SingletonEmployee.getInstance().getEmployee().getFirstName());
        controller.getEmployeeLastName().setText(SingletonEmployee.getInstance().getEmployee().getLastName());

        ArrayList<String> roles = SingletonEmployee.getInstance().getEmployee().getRoles();
        controller.getEmployeeRoles().setText(roles.toString());

        //TODO da aggiungere attivazioni bottoni per amministratore
        if (roles.contains(Scout.class.getSimpleName())) {
            controller.getScoutFilteredSearchForm().setDisable(false);
            controller.getScoutAddRequestForm().setDisable(false);
        }

        if (roles.contains(Designer.class.getSimpleName())) {
            controller.getManagePacketsForm().setDisable(false);
        }
    }
}
