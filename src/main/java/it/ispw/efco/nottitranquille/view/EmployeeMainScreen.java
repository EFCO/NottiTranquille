package it.ispw.efco.nottitranquille.view;

import it.ispw.efco.nottitranquille.SingletonEmployee;
import it.ispw.efco.nottitranquille.controller.MainFX;
import it.ispw.efco.nottitranquille.model.Designer;
import it.ispw.efco.nottitranquille.model.Person;
import it.ispw.efco.nottitranquille.model.Request;
import it.ispw.efco.nottitranquille.model.Scout;
import it.ispw.efco.nottitranquille.model.enumeration.RequestStatus;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.ArrayList;

/**
 * This view shows the manage panel of employee.
 *
 * @see it.ispw.efco.nottitranquille.model.Role
 * @see Scout
 * @see it.ispw.efco.nottitranquille.controller.ApproveInsertRequest
 */
public class EmployeeMainScreen extends LoggedApplication {

    /**
     * Default constructor
     */
    public EmployeeMainScreen() {
    }

    @Override
    public void start(Stage stage) throws Exception {
        super.start(stage);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/mainScreen.fxml"));

        Parent root = (Parent) loader.load();
        stage.setScene(new Scene(root));
        stage.show();

        // Centers to screen
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);

        MainFX controller = loader.getController();
        controller.setMainStage(stage);

        // Sets employee name
        controller.employeeFullName.setText(SingletonEmployee.getInstance().getEmployee().getFullName());

        // Sets roles
        ArrayList<String> roles = SingletonEmployee.getInstance().getEmployee().getRoles();
        controller.employeeRoles.setText(roles.toString());

        //TODO da aggiungere attivazioni bottoni per amministratore
        if (roles.contains(Scout.class.getSimpleName())) {
            controller.scoutFilteredSearchForm.setDisable(false);
            /*controller.scoutAddRequestForm.setDisable(false);*/
        }

        if (roles.contains(Designer.class.getSimpleName())) {
            controller.managePacketsForm.setDisable(false);
        }
    }
}
