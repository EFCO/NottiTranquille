package it.ispw.efco.nottitranquille.view;

import it.ispw.efco.nottitranquille.controller.EmployeeDetails;
import it.ispw.efco.nottitranquille.model.Person;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Federico on 14/07/2016.
 */
public class ManageEmployeesDetails extends Application {


    private Person administrator;

    private LoginBean loggedAdministrator;

    private Person revisionedEmployee;

    public ManageEmployeesDetails() {
    }

    public void setAdministrator(Person administrator) {
        this.administrator = administrator;
    }

    public void setLoggedAdministrator(LoginBean loggedAdministrator) {
        this.loggedAdministrator = loggedAdministrator;
    }

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/fxml/ManageEmployeesDetail.fxml"));

        Parent root = (Parent) loader.load();
        stage.setScene(new Scene(root));
        stage.show();

        EmployeeDetails controller = loader.getController();

        controller.setMainStage(stage);
        controller.setAdministrator(administrator);
        controller.setLoggedAdministrator(loggedAdministrator);
        controller.setrevisionedEmployee(revisionedEmployee);

    }

    public void setRevisionedEmployee(Person revisionedEmployee) {
        this.revisionedEmployee = revisionedEmployee;
    }
}
