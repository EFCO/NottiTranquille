package it.ispw.efco.nottitranquille.controller;

import it.ispw.efco.nottitranquille.model.Person;
import it.ispw.efco.nottitranquille.view.AccessForm;
import it.ispw.efco.nottitranquille.view.LoginBean;
import it.ispw.efco.nottitranquille.view.ManageEmployeesList;
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

    public Text getEmployeeFirstName() {
        return employeeFirstName;
    }

    public Text getEmployeeLastName() {
        return employeeLastName;
    }

    public Text getEmployeeRoles() {
        return employeeRoles;
    }

    public Button getScoutFilteredSearchForm() {
        return ScoutFilteredSearchForm;
    }

    public Button getScuoutAddRequestForm() {
        return scuoutAddRequestForm;
    }

    public Button getManagePacketsForm() {
        return managePacketsForm;
    }

    public Button getManageEmployeesForm() {
        return manageEmployeesForm;
    }

    public Button getManageGlobalSettingsForm() {
        return manageGlobalSettingsForm;
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

    public void manageEmployee(ActionEvent actionEvent) throws Exception {
        ManageEmployeesList manageEmployeeList = new ManageEmployeesList();
        manageEmployeeList.setAdministrator(employee);
        manageEmployeeList.setLoggedAdministrator(loggedEmployee);
        manageEmployeeList.start(mainStage);
    }
}
