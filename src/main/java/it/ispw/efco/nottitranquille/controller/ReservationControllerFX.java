package it.ispw.efco.nottitranquille.controller;

import com.sun.javaws.exceptions.InvalidArgumentException;
import it.ispw.efco.nottitranquille.model.Person;
import it.ispw.efco.nottitranquille.model.Reservation;
import it.ispw.efco.nottitranquille.model.enumeration.ReservationType;
import it.ispw.efco.nottitranquille.view.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.apache.commons.logging.Log;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * Created by emanuele on 17/07/16.
 */
public class ReservationControllerFX {

    private Person user;

    private LoginBean loginBean;

    private LocationBean locationBean;

    private Stage mainStage;

    @FXML
    private TextField fromField;

    @FXML
    private TextField toField;

    @FXML
    private Text titleField;

    @FXML
    private Text addressField;

    @FXML
    private Text priceField;

    @FXML
    private Text error_msg;


    public void setUser(Person user) {
        this.user = user;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public void setLocationBean(LocationBean locationBean) {
        this.locationBean = locationBean;
    }

    public ReservationControllerFX() {
    }


    public void init() {
        titleField.setText(locationBean.getName());
        addressField.setText(locationBean.getAddress());
        priceField.setText(locationBean.getPrice().toString());
    }

    @FXML
    public void reserve() {
        String from = fromField.getText();
        String to = toField.getText();

        try {
            Interval interval = createInterval(from, to);

            ReservationController controller = ReservationController.getInstance();
            Reservation reservation = controller.createReservation(loginBean.getUsername(), new Long(locationBean.getId()),
                    interval, null);

            if (locationBean.getReservationType() == ReservationType.Direct.getText()) {
                PaymentFormScreen paymentFormScreen = new PaymentFormScreen();
                paymentFormScreen.setUser(user);
                paymentFormScreen.setLoginBean(loginBean);
                paymentFormScreen.setReservation(reservation);
                paymentFormScreen.start(mainStage);

            } else if (locationBean.getReservationType() == ReservationType.WithConfirm.getText()) {
                UserScreen userScreen = new UserScreen();
                userScreen.setUser(user);
                userScreen.setLoginBean(loginBean);
                userScreen.start(mainStage);
            }

        } catch (Exception e) {
            e.printStackTrace();
            error_msg.setDisable(true);
            error_msg.setText("Invalid dates format!");
        }


    }

    private Interval createInterval(String from, String to) throws Exception {

        try {

            DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("dd-MM-yyyy");

            DateTime da = DateTime.parse(from, dateTimeFormatter);
            DateTime a = DateTime.parse(to, dateTimeFormatter);

            return new Interval(da, a);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getCause());
        }

    }


}
