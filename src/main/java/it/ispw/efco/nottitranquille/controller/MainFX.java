package it.ispw.efco.nottitranquille.controller;

import it.ispw.efco.nottitranquille.view.UserAccessForm;
import it.ispw.efco.nottitranquille.view.ScoutFilteredSearchForm;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

/**
 *
 */
public class MainFX extends SingleWindowsLogged {

    @FXML
    public Text employeeFullName;
    @FXML
    public Text employeeRoles;
    @FXML
    public Button scoutFilteredSearchForm;
    @FXML
    public Button scoutAddRequestForm;
    @FXML
    public Button managePacketsForm;
    @FXML
    public Button manageEmployeesForm;
    @FXML
    public Button manageGlobalSettingsForm;
    @FXML
    public Button logoutButton;

    /**
     *
     */
    @FXML
    public void logoutHandler() {
        logout();

        UserAccessForm userAccessForm = new UserAccessForm();
        try {
            // Removes close event
            getMainStage().setOnCloseRequest(null);
            // Goes to access form
            userAccessForm.start(getMainStage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     */
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
