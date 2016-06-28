package it.ispw.efco.nottitranquille.controller;

import it.ispw.efco.nottitranquille.model.Person;
import it.ispw.efco.nottitranquille.view.AccessForm;
import it.ispw.efco.nottitranquille.view.LoginBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by Federico on 28/06/2016.
 */
public class MainScreenController {

    private Stage mainStage;

    private Person employee;

    private LoginBean loggedEmployee;

    @FXML
    private Text employeeFirstName;
    @FXML
    private Text employeeLastName;
    @FXML
    private Text employeeRoles;
    @FXML
    private Button ScoutFilteredSearchForm;
    @FXML
    private Button scuoutAddRequestForm;
    @FXML
    private Button managePacketsForm;
    @FXML
    private Button manageEmployeesForm;
    @FXML
    private Button manageGlobalSettingsForm;

    public void setEmployee(Person employee) {
        this.employee = employee;
    }

    public void setLoggedEmployee(LoginBean loggedEmployee) {
        this.loggedEmployee = loggedEmployee;
    }

    public void setMainStage(Stage stage) {
        this.mainStage = stage;
    }


    public void logoutHandler(ActionEvent actionEvent) {
        try {
            AccessController.setLogout(loggedEmployee.getId());
            AccessForm accessForm = new AccessForm();
            accessForm.start(mainStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
