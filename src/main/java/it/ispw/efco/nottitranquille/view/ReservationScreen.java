package it.ispw.efco.nottitranquille.view;

import it.ispw.efco.nottitranquille.controller.ReservationControllerFX;
import it.ispw.efco.nottitranquille.model.Person;
import it.ispw.efco.nottitranquille.model.Role;
import it.ispw.efco.nottitranquille.model.dao.UserDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by emanuele on 17/07/16.
 */
public class ReservationScreen extends Application {

    private Person user;

    private LoginBean loginBean;

    private LocationBean locationBean;

    public void setUser(Person user) {
        this.user = user;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public void setLocationBean(LocationBean locationBean) {
        this.locationBean = locationBean;
    }

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/fxml/ReservationScreen.fxml"));

        Parent root = (Parent) loader.load();
        stage.setScene(new Scene(root));
        stage.show();

        ReservationControllerFX controller = loader.getController();


            /* Set loginBean and locationBean */
        LocationBean locationBean = new LocationBean();
        locationBean.populate("8");


        Person tenant = UserDAO.findBy("Zanna");

        LoginBean loginBean = new LoginBean();
        loginBean.setUsername(tenant.getUsername());
        loginBean.setPassword(tenant.getPassword());

        List<String> roleString = new ArrayList<String>();
        for (Role roles : tenant.getRoles())
            roleString.add(roles.getClass().getSimpleName());

        loginBean.setLogged(true);

        /* Create ReservationScreen*/

        ReservationScreen reservationScreen = new ReservationScreen();
        reservationScreen.setLoginBean(loginBean);
        reservationScreen.setUser(tenant);
        reservationScreen.setLocationBean(locationBean);

        controller.setUser(tenant);
        controller.setLoginBean(loginBean);
        controller.setLocationBean(locationBean);

        controller.init();
        controller.setMainStage(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
