package it.ispw.efco.nottitranquille.controller;

import it.ispw.efco.nottitranquille.model.Person;
import it.ispw.efco.nottitranquille.model.PersonDAO;
import it.ispw.efco.nottitranquille.view.AccessForm;
import it.ispw.efco.nottitranquille.view.LoginBean;
import it.ispw.efco.nottitranquille.view.ManageEmployeesDetails;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Federico on 14/07/2016.
 */
public class EmployeesList implements Initializable {

    private Stage mainStage;

    private Person Administrator;

    private LoginBean loggedAdministrator;

    public MenuItem bClose;
    public MenuItem bAdd;
    public MenuItem bModify;
    public MenuItem bDelete;
    public MenuItem bLogOut;
    public TableView<Person> lvEmployeesList;

    PersonDAO personDAO;

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


    public void initialize(URL url, ResourceBundle resourceBundle) {
        personDAO = new PersonDAO();
        List<Person> results = personDAO.selectEmployees();
        List<Person> employees = new ArrayList<Person>();
        for (Person p : results) {
            if (p.getRole("Administrator") != null || p.getRole("Designer") != null || p.getRole("Scout") != null) {
                employees.add(p);
            }

        }
        ObservableList rows = FXCollections.observableList(employees);
        lvEmployeesList.setItems(rows);
        TableColumn<Person, String> nameCol = new TableColumn<Person, String>("Name");
        nameCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Person, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Person, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getFirstName());
            }
        });
        TableColumn<Person, String> surnameCol = new TableColumn<Person, String>("Surname");
        surnameCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Person, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Person, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getLastName());
            }
        });
        TableColumn<Person, String> roleCol = new TableColumn<Person, String>("Role");
        roleCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Person, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Person, String> p) {
                try {
                    return new ReadOnlyObjectWrapper(p.getValue().getMainRole());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
        lvEmployeesList.getColumns().setAll(nameCol, surnameCol, roleCol);

        lvEmployeesList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                if (lvEmployeesList.getSelectionModel().getSelectedItem() != null) {
                    bDelete.setDisable(false);
                    bModify.setDisable(false);
                }
            }
        });
    }

    public void addEmployee(ActionEvent actionEvent) {
        ManageEmployeesDetails med = new ManageEmployeesDetails();
        med.setAdministrator(this.Administrator);
        med.setLoggedAdministrator(this.loggedAdministrator);
        try {
            med.start(mainStage);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void modifyEmployee(ActionEvent actionEvent) {
        ManageEmployeesDetails med = new ManageEmployeesDetails();
        med.setAdministrator(this.Administrator);
        med.setLoggedAdministrator(this.loggedAdministrator);
        med.setRevisionedEmployee(this.lvEmployeesList.getSelectionModel().getSelectedItem());
        System.out.println("Impiegato da cambiare:" + lvEmployeesList.getSelectionModel().getSelectedItem().getLastName());
        try {
            med.start(mainStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeEmployee(ActionEvent actionEvent) {
        personDAO.remove(lvEmployeesList.getSelectionModel().getSelectedItem());
    }
}
