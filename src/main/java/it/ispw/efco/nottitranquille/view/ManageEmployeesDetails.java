package it.ispw.efco.nottitranquille.view;

import it.ispw.efco.nottitranquille.controller.EmployeeDetails;
import it.ispw.efco.nottitranquille.model.Person;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.joda.time.format.DateTimeFormat;

import java.util.ArrayList;
import java.util.List;

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

        List<String> allRoles = new ArrayList<String>();
        allRoles.add("Administrator");
        allRoles.add("Designer");
        allRoles.add("Scout");
        controller.getSpRole().setItems(FXCollections.observableList(allRoles));

        ObservableList<MenuItem> allAuth = FXCollections.observableArrayList();
        allAuth.add(new MenuItem("Administrator_Privileges"));
        allAuth.add(new MenuItem("Manage_Packets"));
        allAuth.add(new MenuItem("Insert_Requests"));
        allAuth.add(new MenuItem("Approve_Requests"));
        controller.getSpAddAuth().getItems().addAll(allAuth);
        List<String> roles = new ArrayList<String>();
        if (revisionedEmployee != null) {
            controller.getTvName().setText(revisionedEmployee.getFirstName());
            controller.getTvSurname().setText(revisionedEmployee.getLastName());
            controller.getTvMail().setText(revisionedEmployee.getEmail());
            controller.getTvPhone().setText(revisionedEmployee.getPhoneNumber());
            controller.getTvBirthdate().setText(revisionedEmployee.getBirthdate().toString(DateTimeFormat.forPattern("dd-MM-yyyy")));
            controller.getSpGender().setValue(revisionedEmployee.getGender());
            controller.getTvUsername().setText(revisionedEmployee.getUsername());
            controller.getTvPassword().setText(revisionedEmployee.getPassword());
            controller.getTvNation().setText(revisionedEmployee.getAddress().getNation());
            controller.getTvCity().setText(revisionedEmployee.getAddress().getCity());
            controller.getTvAddress().setText(revisionedEmployee.getAddress().getAddress());
            controller.getTvPostalCode().setText(revisionedEmployee.getAddress().getPostalcode());




            System.out.println("Old roles:" + revisionedEmployee.getRoles());
            for (String r : revisionedEmployee.getRoles()) {
                System.out.println(r);
                if (r.equals("Administrator")) {
                    roles.add("Administrator_Privileges");
                } else if (r.equals("Designer")) {
                    roles.add("Manage_Packets");
                } else if (r.equals("Manager")) {
                    roles.add("Insert_Requests");
                } else if (r.equals("Scout")) {
                    roles.add("Approve_Requests");
                }
            }


            controller.getSpAddAuth().setDisable(false);
        }


        ObservableList rows = FXCollections.observableList(roles);
        controller.getLvAuth().setItems(rows);
        TableColumn<String, String> authCol = new TableColumn<String, String>("Authorization");
        authCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<String, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue());
            }
        });


        controller.getLvAuth().getColumns().setAll(authCol);

        if (revisionedEmployee != null)
            controller.getSpRole().setValue(revisionedEmployee.getMainRole());

//        EventHandler<ActionEvent> action = controller.addRole();
//
//        for (MenuItem mi : controller.getSpAddAuth().getItems()) {
//            System.out.println("aggiungo action");
//            mi.setOnAction(action);
//        }


        controller.setMainStage(stage);
        controller.setAdministrator(administrator);
        controller.setLoggedAdministrator(loggedAdministrator);
        if (revisionedEmployee != null) {
            controller.setrevisionedEmployee(revisionedEmployee);
        }

    }

    public void setRevisionedEmployee(Person revisionedEmployee) {
        this.revisionedEmployee = revisionedEmployee;
    }


}
