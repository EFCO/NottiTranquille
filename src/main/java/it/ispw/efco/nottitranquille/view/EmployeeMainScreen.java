package it.ispw.efco.nottitranquille.view;

import it.ispw.efco.nottitranquille.controller.MainScreenController;
import it.ispw.efco.nottitranquille.model.Designer;
import it.ispw.efco.nottitranquille.model.Person;
import it.ispw.efco.nottitranquille.model.Scout;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Created by Federico on 28/06/2016.
 */
public class EmployeeMainScreen extends Application {

    private Person employee;

    private LoginBean loggedEmployee;

    public EmployeeMainScreen() {
    }

    public void setEmployee(Person employee) {
        this.employee = employee;
    }

    public void setLoggedEmployee(LoginBean loggedEmployee) {
        this.loggedEmployee = loggedEmployee;
    }

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/fxml/EmployeeMainScreen.fxml"));

        Parent root = (Parent) loader.load();
        stage.setScene(new Scene(root));
        stage.show();

        MainScreenController controller = loader.getController();
        controller.getEmployeeFirstName().setText(employee.getFirstName());
        controller.getEmployeeLastName().setText(employee.getLastName());
        ArrayList<String> roles = employee.getRoles();
        controller.getEmployeeRoles().setText(roles.toString());

        //TODO da aggiungere attivazioni bottoni per amministratore
        if (roles.contains(Scout.class.getSimpleName())) {
            controller.getScoutFilteredSearchForm().setDisable(false);
            controller.getScuoutAddRequestForm().setDisable(false);
        }

        if (roles.contains(Designer.class.getSimpleName())) {
            controller.getManagePacketsForm().setDisable(false);
        }

        controller.setMainStage(stage);
        controller.setEmployee(employee);
        controller.setLoggedEmployee(loggedEmployee);
    }

}
