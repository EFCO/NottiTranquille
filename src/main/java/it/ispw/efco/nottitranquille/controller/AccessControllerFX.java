package it.ispw.efco.nottitranquille.controller;

import it.ispw.efco.nottitranquille.model.Person;
import it.ispw.efco.nottitranquille.view.EmployeeMainScreen;
import it.ispw.efco.nottitranquille.view.LoginBean;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by Federico on 25/06/2016.
 */
public class AccessControllerFX {

    public AccessControllerFX() {
    }

    private Stage mainStage;

    @FXML
    private TextField username_textfield;

    @FXML
    private PasswordField password_textfield;

    @FXML
    private Button login_button;

    @FXML
    public Text error_message;

    public void setMainStage(Stage stage) {
        this.mainStage = stage;
    }

    @FXML
    protected void handleLogin() {
        String username = username_textfield.getText();
        String password = password_textfield.getText();
        System.out.println(username + password);
        LoginBean lb = new LoginBean();
        lb.setUsername(username);
        lb.setPassword(password);
        Person employee = AccessController.getRegisteredUser(username,password);
        if (employee != null) {
            if (AccessController.isAlreadyLogged(lb, "") == 2) {
                error_message.setVisible(false);
                AccessController.logNewUser(lb);
                try {
                    EmployeeMainScreen employeeMainScreen = new EmployeeMainScreen();
                    employeeMainScreen.setEmployee(employee);
                    employeeMainScreen.setLoggedEmployee(lb);
                    employeeMainScreen.start(mainStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                error_message.setVisible(true);
                username_textfield.setText("");
                password_textfield.setText("");
            }
        } else {
            error_message.setVisible(true);
            username_textfield.setText("");
            password_textfield.setText("");
        }
    }


}
