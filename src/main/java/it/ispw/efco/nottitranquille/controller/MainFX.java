package it.ispw.efco.nottitranquille.controller;

import it.ispw.efco.nottitranquille.view.AccessForm;
import it.ispw.efco.nottitranquille.view.ScoutFilteredSearchForm;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class MainFX extends SingleWindowsLogged {

    @FXML
    private Text employeeFirstName;
    @FXML
    private Text employeeLastName;
    @FXML
    private Text employeeRoles;
    @FXML
    private Button scoutFilteredSearchForm;
    @FXML
    private Button scoutAddRequestForm;
    @FXML
    private Button managePacketsForm;
    @FXML
    private Button manageEmployeesForm;
    @FXML
    private Button manageGlobalSettingsForm;

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
        return scoutFilteredSearchForm;
    }

    public Button getScoutAddRequestForm() {
        return scoutAddRequestForm;
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

    @FXML
    public void logoutHandler() {
        logout();

        AccessForm accessForm = new AccessForm();
        try {
            // Removes close event
            getMainStage().setOnCloseRequest(null);
            // Goes to access form
            accessForm.start(getMainStage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void showScoutFilteredSearchForm() {
        ScoutFilteredSearchForm scoutFilteredSearchForm = new ScoutFilteredSearchForm();
        try {
            scoutFilteredSearchForm.start(getMainStage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
