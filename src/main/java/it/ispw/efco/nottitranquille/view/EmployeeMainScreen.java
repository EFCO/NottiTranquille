package it.ispw.efco.nottitranquille.view;

import it.ispw.efco.nottitranquille.controller.MainScreenController;
import it.ispw.efco.nottitranquille.model.Person;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
        controller.setMainStage(stage);
        controller.setEmployee(employee);
        controller.setLoggedEmployee(loggedEmployee);
    }

}
