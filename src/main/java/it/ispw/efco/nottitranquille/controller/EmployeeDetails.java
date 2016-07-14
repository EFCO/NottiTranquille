package it.ispw.efco.nottitranquille.controller;

import it.ispw.efco.nottitranquille.model.Person;
import it.ispw.efco.nottitranquille.view.AccessForm;
import it.ispw.efco.nottitranquille.view.LoginBean;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Federico on 14/07/2016.
 */
public class EmployeeDetails implements Initializable {

    private Stage mainStage;

    private Person Administrator;

    private LoginBean loggedAdministrator;

    private Person revisionedEmployee;

    public MenuItem bClose;
    public MenuItem bLogOut;
    public TextField tvName;
    public TextField tvSurname;
    public TextField tvMail;
    public TextField tvPhone;
    public TextField tvNation;
    public TextField tvCity;
    public TextField tvAddress;
    public TextField tvPostalCode;
    public ComboBox spRole;
    public Button bAccept;
    public ListView lvAuth;
    public Button bRemoveAuth;
    public MenuButton spAddAuth;

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public void setAdministrator(Person administrator) {
        Administrator = administrator;
    }

    public void setLoggedAdministrator(LoginBean loggedAdministrator) {
        this.loggedAdministrator = loggedAdministrator;
    }


    public void logoutHandler(ActionEvent actionEvent) {
        try {
            AccessController.setLogout(loggedAdministrator.getId());
            AccessForm accessForm = new AccessForm();
            accessForm.start(mainStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setrevisionedEmployee(Person revisionedEmployee) {
        this.revisionedEmployee = revisionedEmployee;
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (revisionedEmployee != null) {
            tvName.setText(revisionedEmployee.getFirstName());
            tvSurname.setText(revisionedEmployee.getLastName());
            tvMail.setText(revisionedEmployee.getEmail());
        }
    }
}
