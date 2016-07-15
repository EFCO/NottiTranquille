package it.ispw.efco.nottitranquille.controller;

import it.ispw.efco.nottitranquille.SingletonEmployee;
import it.ispw.efco.nottitranquille.model.Person;
import it.ispw.efco.nottitranquille.view.MainScreen;
import it.ispw.efco.nottitranquille.view.LoginBean;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class AccessFX extends SingleWindow {

    public AccessFX() {
    }

    @FXML
    private ProgressIndicator progressIndicator;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Button loginButton;

    @FXML
    public Text errorMessage;

    @FXML
    protected void handleLogin() {
        progressIndicator.setVisible(true);

        final String username = usernameTextField.getText();
        final String password = passwordTextField.getText();

        final LoginBean loginBean = new LoginBean();
        loginBean.setUsername(username);
        loginBean.setPassword(password);


        Thread fxThread = new Thread() {
            public void run() {
                Person employee = Access.getRegisteredUser(username, password);

                if (employee != null) {
                    if (Access.isAlreadyLogged(loginBean, "") != 0) {
                        errorMessage.setVisible(false);
                        Access.logNewUser(loginBean);

                        // Sets singleton with login data
                        SingletonEmployee.getInstance().setEmployee(employee);
                        SingletonEmployee.getInstance().setLoggedEmployee(loginBean);

                        // Pass to new Screen
                        Platform.runLater(new Runnable() {
                            public void run() {
                                try {
                                    MainScreen mainScreen = new MainScreen();
                                    mainScreen.start(getMainStage());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                }
                // Sets error and clean text
                Platform.runLater(new Runnable() {
                    public void run() {
                        errorMessage.setVisible(true);
                        progressIndicator.setVisible(false);
                        usernameTextField.setText("");
                        passwordTextField.setText("");
                    }
                });
            }
        };

        fxThread.setDaemon(true);
        fxThread.start();
    }
}
