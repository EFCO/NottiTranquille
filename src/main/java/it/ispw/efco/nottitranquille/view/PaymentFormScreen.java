package it.ispw.efco.nottitranquille.view;

import it.ispw.efco.nottitranquille.controller.PaymentControllerFX;
import it.ispw.efco.nottitranquille.controller.TenantResSummaryFX;
import it.ispw.efco.nottitranquille.model.Person;
import it.ispw.efco.nottitranquille.model.Reservation;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by emanuele on 16/07/16.
 */
public class PaymentFormScreen extends Application {

    private Person user;

    private LoginBean loginBean;

    private Reservation reservation;

    public PaymentFormScreen() {
    }

    public void setUser(Person user) {
        this.user = user;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/fxml/PaymentForm.fxml"));

        Parent root = (Parent) loader.load();
        stage.setScene(new Scene(root));
        stage.show();

        PaymentControllerFX controller = loader.getController();

        controller.setUser(user);
        controller.setLoginBean(loginBean);
        controller.setReservation(reservation);

        controller.init();
        controller.setMainStage(stage);
    }
}

