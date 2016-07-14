package it.ispw.efco.nottitranquille.view;

import it.ispw.efco.nottitranquille.controller.EmployeesList;
import it.ispw.efco.nottitranquille.model.Person;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Federico on 14/07/2016.
 */
public class ManageEmployeesList extends Application {


    private Person administrator;

    private LoginBean loggedAdministrator;


    public ManageEmployeesList() {


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
                "/fxml/ManageEmployeesMain.fxml"));

        Parent root = (Parent) loader.load();
        stage.setScene(new Scene(root));
        stage.show();

        EmployeesList controller = loader.getController();

        if (controller.lvEmployeesList.getSelectionModel().getSelectedItem() != null) {
            controller.bDelete.setDisable(false);
            controller.bModify.setDisable(false);
        }


        controller.setMainStage(stage);
        controller.setAdministrator(administrator);
        controller.setLoggedAdministrator(loggedAdministrator);
    }

}
