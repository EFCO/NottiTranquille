package it.ispw.efco.nottitranquille.controller;

import it.ispw.efco.nottitranquille.model.Person;
import it.ispw.efco.nottitranquille.model.Role;
import it.ispw.efco.nottitranquille.view.LoginBean;
import it.ispw.efco.nottitranquille.view.UserScreen;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * Listener for the login Button
     */
    @FXML
    protected void handleLogin() {

        /* Get username and password from the view field */
        String username = username_textfield.getText();
        String password = password_textfield.getText();

        LoginBean lb = new LoginBean(); // create a LoginBean for user data
        lb.setUsername(username);
        lb.setPassword(password);

        /* ask controller for login*/
        Person user = LoginController.getInstance().login(username, password);
        lb.setLogged(true); // JavaBean for the user is set on logged

        /* get all roles for the user and put them to the javaBean as String */
        List<Role> roles = user.getRoles();
        List<String> roleStrings = new ArrayList<String>();
        for (Role role : roles)
            roleStrings.add(role.getClass().getSimpleName());

        lb.setRoles(roleStrings);

        if (lb.isLogged()) {
            error_message.setVisible(false);
            try {
                /* Switch on the main screen for logged user */
                UserScreen userScreen = new UserScreen();
                userScreen.setUser(user);
                userScreen.setLoginBean(lb);
                userScreen.start(mainStage);

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            /* show error messahe */
            error_message.setVisible(true);
            username_textfield.setText("");
            password_textfield.setText("");
        }

    }


}
