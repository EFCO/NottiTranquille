package it.ispw.efco.nottitranquille.controller;

import it.ispw.efco.nottitranquille.model.Person;
import it.ispw.efco.nottitranquille.view.LocationListScreen;
import it.ispw.efco.nottitranquille.view.LoginBean;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
        Person user = LoginController.getInstance().login(username, password);
        if (user != null) {
            if (lb.isLogged()) {
                error_message.setVisible(false);
                try {
                    LocationListScreen locationListScreen = new LocationListScreen();
                    locationListScreen.setUser(user);
                    locationListScreen.setBean(lb);
                    locationListScreen.start(mainStage);

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
