package it.ispw.efco.nottitranquille.controller;

import it.ispw.efco.nottitranquille.model.*;
import it.ispw.efco.nottitranquille.model.enumeration.Gender;
import it.ispw.efco.nottitranquille.view.AccessForm;
import it.ispw.efco.nottitranquille.view.LoginBean;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Federico on 14/07/2016.
 */
public class EmployeeDetails implements Initializable {

    public Text errAlert;

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
    public TextField tvBirthdate;
    public ComboBox spGender;
    public TextField tvUsername;
    public TextField tvPassword;
    public TextField tvNation;
    public TextField tvCity;
    public TextField tvAddress;
    public TextField tvPostalCode;
    public ComboBox spRole;
    public Button bAccept;
    public TableView<String> lvAuth;
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


        List<String> genders = new ArrayList<String>();
        for (Gender g : Gender.values()) {
            genders.add(g.toString());
        }
        spGender.setItems(FXCollections.observableList(genders));
//        System.out.println("Initializing" + revisionedEmployee.getLastName());
//        List<String> allRoles = new ArrayList<String>();
//        allRoles.add("Administrator");
//        allRoles.add("Designer");
//        allRoles.add("Scout");
//        spRole.setItems(FXCollections.observableList(allRoles));
//
//        ObservableList<CheckMenuItem> allAuth = FXCollections.observableArrayList();
//        allAuth.add(new CheckMenuItem("Administrator_Privileges"));
//        allAuth.add(new CheckMenuItem("Manage_Packets"));
//        allAuth.add(new CheckMenuItem("Insert_Requests"));
//        allAuth.add(new CheckMenuItem("Approve_Requests"));
//        spAddAuth.getItems().addAll( allAuth);
//
//        if (revisionedEmployee != null) {
//            tvName.setText(revisionedEmployee.getFirstName());
//            tvSurname.setText(revisionedEmployee.getLastName());
//            tvMail.setText(revisionedEmployee.getEmail());
//            tvPhone.setText(revisionedEmployee.getPhoneNumber());
//            tvBirthdate.setText(revisionedEmployee.getBirthdate().toString(DateTimeFormat.forPattern("dd-MM-yyyy")));
//            spGender.setValue(revisionedEmployee.getGender());
//            tvUsername.setText(revisionedEmployee.getUsername());
//            tvPassword.setText(revisionedEmployee.getPassword());
//            tvNation.setText(revisionedEmployee.getAddress().getNation());
//            tvCity.setText(revisionedEmployee.getAddress().getCity());
//            tvAddress.setText(revisionedEmployee.getAddress().getAddress());
//            tvPostalCode.setText(revisionedEmployee.getAddress().getPostalcode());
//
//            spRole.setValue(revisionedEmployee.getMainRole());
//
//            List<String> roles =new ArrayList<String>();
//            for (String r : revisionedEmployee.getRoles()){
//                if (r.equals("administrator")){
//                    roles.add("Administrator_Privileges");
//                } else if (r.equals("designer")){
//                    roles.add("Manage_Packets");
//                } else if (r.equals("manager")){
//                    roles.add("Insert_Requests");
//                } else if (r.equals("scout")){
//                    roles.add("Approve_Requests");
//                }
//            }
//            ObservableList rows = FXCollections.observableList(roles);
//            lvAuth.setItems(rows);
//            TableColumn<String, String> nameCol = new TableColumn<String, String>("Authorization");
//            nameCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String, String>, ObservableValue<String>>() {
//                public ObservableValue<String> call(TableColumn.CellDataFeatures<String, String> p) {
//                    return new ReadOnlyObjectWrapper(p.getValue());
//                }
//            });
//
//
//        }

        spRole.valueProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue ov, String t, String t1) {
                if (t1.equals("administrator") && !lvAuth.getItems().contains("Administrator_Privileges")) {
                    lvAuth.getItems().add("Administrator_Privileges");
                } else if (t1.equals("designer") && !lvAuth.getItems().contains("Manage_Packets")) {
                    lvAuth.getItems().add("Manage_Packets");
                } else if (t1.equals("manager") && !lvAuth.getItems().contains("Insert_Requests")) {
                    lvAuth.getItems().add("Insert_Requests");
                } else if (t1.equals("scout") && !lvAuth.getItems().contains("Approve_Requests")) {
                    lvAuth.getItems().add("Approve_Requests");
                }
                spAddAuth.setDisable(false);
            }
        });

        lvAuth.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                if (lvAuth.getSelectionModel().getSelectedItem() != null) {
                    bRemoveAuth.setDisable(false);
                }
            }
        });


    }

    public void addRole(ActionEvent actionEvent) {
        for (MenuItem item : spAddAuth.getItems()) {
            CheckMenuItem checkMenuItem = (CheckMenuItem) item;
            if (checkMenuItem.isSelected() && !lvAuth.getItems().contains(item.getText())) {
                lvAuth.getItems().add(item.getText());
            }
        }
    }

    public void removeRole(ActionEvent actionEvent) {
        List<String> auths = new ArrayList<String>();
        auths = lvAuth.getItems();
        for (MenuItem item : spAddAuth.getItems()) {
            CheckMenuItem checkMenuItem = (CheckMenuItem) item;
            if (checkMenuItem.isSelected()) {
                for (String r : auths) {
                    if (r.equals(checkMenuItem.toString())) {
                        lvAuth.getItems().remove(r);
                    }
                }
            }
        }
    }

    public void saveEmployee(ActionEvent actionEvent) {
        if (!tvName.getText().equals("") && !tvSurname.getText().equals("") && !tvMail.getText().equals("") && !tvPhone.getText().equals("") && !tvBirthdate.getText().equals("") && !spGender.getValue().equals("") && !tvUsername.getText().equals("") && !tvPassword.getText().equals("") && !tvNation.getText().equals("") && !tvCity.getText().equals("") && !tvAddress.getText().equals("") && !tvPostalCode.getText().equals("") && !spRole.getValue().toString().equals("")) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("dd-MM-yyyy");
            Address addr = new Address(tvNation.getText(), tvCity.getText(), tvAddress.getText(), tvPostalCode.getText());
            if (revisionedEmployee != null) {
                revisionedEmployee.setFirstName(tvName.getText());
                revisionedEmployee.setLastName(tvSurname.getText());
                revisionedEmployee.setEmail(tvMail.getText());
                revisionedEmployee.setPhoneNumber(tvPhone.getText());
                revisionedEmployee.setBirthdate(DateTime.parse(tvBirthdate.getText(), dateTimeFormatter));
                revisionedEmployee.setGender(Gender.valueOf(spGender.getValue().toString()));
                revisionedEmployee.setUsername(tvUsername.getText());
                revisionedEmployee.setPassword(tvPassword.getText());
                revisionedEmployee.setAddress(addr);
                revisionedEmployee.setMainRole(spRole.getValue().toString());

                List<String> oldroles = revisionedEmployee.getRoles();
                List<String> newroles = lvAuth.getItems();
                String roleAuth = "";
                for (String r : oldroles) {

                    if (r.equals("administrator")) {
                        roleAuth = "Administrator_Privileges";
                    } else if (r.equals("designer")) {
                        roleAuth = "Manage_Packets";
                    } else if (r.equals("manager")) {
                        roleAuth = "Insert_Requests";
                    } else if (r.equals("scout")) {
                        roleAuth = "Approve_Requests";
                    } else {
                        continue;
                    }

                    if (!newroles.contains(roleAuth)) {
                        try {
                            revisionedEmployee.removeRole(r);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    for (String s : lvAuth.getItems()) {
                        if (s.equals("Administrator_Privileges")) {
                            try {
                                revisionedEmployee.addRole(new Administrator());
                            } catch (Exception e) {
                            }
                        }
                        if (s.equals("Manage_Packets")) {
                            try {
                                revisionedEmployee.addRole(new Designer());
                            } catch (Exception e) {
                            }
                        }
                        if (s.equals("Insert_Requests")) {
                            try {
                                revisionedEmployee.addRole(new Manager());
                            } catch (Exception e) {
                            }
                        }
                        if (s.equals("Approve_Requests")) {
                            try {
                                revisionedEmployee.addRole(new Scout());
                            } catch (Exception e) {
                            }
                        }
                    }

                }
            } else {
                revisionedEmployee = new Person(tvName.getText(), tvSurname.getText(), tvMail.getText(), tvPhone.getText(), DateTime.parse(tvBirthdate.getText(), dateTimeFormatter), Gender.valueOf(spGender.getValue().toString()), tvUsername.getText(), tvPassword.getText(), addr, spRole.getValue().toString());
                for (String s : lvAuth.getItems()) {
                    if (s.equals("Administrator_Privileges")) {
                        try {
                            revisionedEmployee.addRole(new Administrator());
                        } catch (Exception e) {
                        }
                    }
                    if (s.equals("Manage_Packets")) {
                        try {
                            revisionedEmployee.addRole(new Designer());
                        } catch (Exception e) {
                        }
                    }
                    if (s.equals("Insert_Requests")) {
                        try {
                            revisionedEmployee.addRole(new Manager());
                        } catch (Exception e) {
                        }
                    }
                    if (s.equals("Approve_Requests")) {
                        try {
                            revisionedEmployee.addRole(new Scout());
                        } catch (Exception e) {
                        }
                    }
                }
            }
            errAlert.setVisible(false);
            PersonDAO personDAO = new PersonDAO();
            personDAO.store(revisionedEmployee);
        } else {
            errAlert.setVisible(true);
        }

    }

    public Text getErrAlert() {
        return errAlert;
    }

    public Stage getMainStage() {
        return mainStage;
    }

    public Person getAdministrator() {
        return Administrator;
    }

    public LoginBean getLoggedAdministrator() {
        return loggedAdministrator;
    }

    public Person getRevisionedEmployee() {
        return revisionedEmployee;
    }

    public MenuItem getbClose() {
        return bClose;
    }

    public MenuItem getbLogOut() {
        return bLogOut;
    }

    public TextField getTvName() {
        return tvName;
    }

    public TextField getTvSurname() {
        return tvSurname;
    }

    public TextField getTvMail() {
        return tvMail;
    }

    public TextField getTvPhone() {
        return tvPhone;
    }

    public TextField getTvBirthdate() {
        return tvBirthdate;
    }

    public ComboBox getSpGender() {
        return spGender;
    }

    public TextField getTvUsername() {
        return tvUsername;
    }

    public TextField getTvPassword() {
        return tvPassword;
    }

    public TextField getTvNation() {
        return tvNation;
    }

    public TextField getTvCity() {
        return tvCity;
    }

    public TextField getTvAddress() {
        return tvAddress;
    }

    public TextField getTvPostalCode() {
        return tvPostalCode;
    }

    public ComboBox getSpRole() {
        return spRole;
    }

    public Button getbAccept() {
        return bAccept;
    }

    public TableView<String> getLvAuth() {
        return lvAuth;
    }

    public Button getbRemoveAuth() {
        return bRemoveAuth;
    }

    public MenuButton getSpAddAuth() {
        return spAddAuth;
    }
}
