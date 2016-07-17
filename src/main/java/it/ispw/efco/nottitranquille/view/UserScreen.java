package it.ispw.efco.nottitranquille.view;

import it.ispw.efco.nottitranquille.controller.UserPageControllerFX;
import it.ispw.efco.nottitranquille.model.Manager;
import it.ispw.efco.nottitranquille.model.Person;
import it.ispw.efco.nottitranquille.model.Role;
import it.ispw.efco.nottitranquille.model.Tenant;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

/**
 * Created by emanuele on 16/07/16.
 */
public class UserScreen extends Application {

    LoginBean loginBean;

    Person user;

    public UserScreen() {
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public void setUser(Person user) {
        this.user = user;
    }

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/fxml/UserScreen.fxml"));

        Parent root = (Parent) loader.load();
        stage.setScene(new Scene(root));
        stage.show();

        UserPageControllerFX controller = loader.getController();
        controller.getUserFirstname().setText(user.getfirstname());
        controller.getUserLastname().setText(user.getlastname());

        List<Role> roles = user.getRoles();

        for (Role role : roles) {

            if (role.getClass().getSimpleName().equals(Tenant.class.getSimpleName())) {
                controller.getTenantButton().setVisible(true);
                controller.getTenantButton().setDisable(false);
            }

            if (role.getClass().getSimpleName().equals(Manager.class.getSimpleName())) {
                controller.getManagerButton().setVisible(true);
                controller.getManagerButton().setDisable(false);
            }
        }

        controller.setUser(user);
        controller.setLb(loginBean);
        controller.setMainStage(stage);
    }



}
