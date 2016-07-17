package it.ispw.efco.nottitranquille.controller;

import it.ispw.efco.nottitranquille.model.Address;
import it.ispw.efco.nottitranquille.model.Person;
import it.ispw.efco.nottitranquille.model.Reservation;
import it.ispw.efco.nottitranquille.view.LoginBean;
import it.ispw.efco.nottitranquille.view.UserScreen;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * Created by emanuele on 16/07/16.
 */
public class PaymentControllerFX {

    private Stage mainStage;

    private Person user;

    private LoginBean loginBean;

    private Reservation reservation;

    @FXML
    private Text addrField;

    @FXML
    private Text fromField;

    @FXML
    private Text toField;

    @FXML
    private Text priceField;

    @FXML
    private Text errorField;

    public void setUser(Person user) {
        this.user = user;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public PaymentControllerFX() {
    }

    public void init() {
        Address address = reservation.getLocation().getStructure().getAddress();
        String addr = address.getCity() + " " + address.getAddress() + " " + address.getPostalcode();

        addrField.setText(addr);
        fromField.setText(String.format("%d/%d/%d",
                reservation.getStartDate().getDayOfMonth(),
                reservation.getStartDate().getMonthOfYear(),
                reservation.getStartDate().getYear()));
        toField.setText(String.format("%d/%d/%d",
                reservation.getEndDate().getDayOfMonth(),
                reservation.getEndDate().getMonthOfYear(),
                reservation.getEndDate().getYear()));

        priceField.setText(Float.toString(reservation.getPrice()));
    }

    @FXML
    public void close() throws Exception {
        loginBean.setLogged(false);
        UserScreen userScreen = new UserScreen();
        userScreen.setLoginBean(loginBean);
        userScreen.setUser(user);
        userScreen.start(mainStage);
    }

    @FXML
    public void pay() {
        PaymentControl control = PaymentControl.getInstance();
        try {

            control.pay(reservation.getId(), loginBean.getUsername());
            close();
        } catch (Exception e) {
            e.printStackTrace();
            errorField.setVisible(true);
            errorField.setText("The range of days are not still available! Sorry!");
        }
    }
}
