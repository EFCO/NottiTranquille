package it.ispw.efco.nottitranquille.controller;

import it.ispw.efco.nottitranquille.model.Person;
import it.ispw.efco.nottitranquille.view.LocationListScreen;
import it.ispw.efco.nottitranquille.view.LoginBean;
import it.ispw.efco.nottitranquille.view.ManagerResSummaryScreen;
import it.ispw.efco.nottitranquille.view.TenantResSummaryScreen;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by emanuele on 16/07/16.
 */
public class UserPageControllerFX {

    private Stage mainStage;

    private Person user;

    private LoginBean lb;

    @FXML
    private Button tenantButton;

    @FXML
    private Button managerButton;

    @FXML
    private Button newReservationButton;

    @FXML
    private Text userFirstname;

    @FXML
    private Text userLastname;

    public UserPageControllerFX() {
    }

    /**
     * Listener for Tenant summary Button
     *
     * @throws Exception
     */
    @FXML
    public void handleTenantButton() throws Exception {
        /* System shows all reservations for the logged tenant
        * so he can procede with payment or delete them */
        TenantResSummaryScreen tenantResSummaryScreen = new TenantResSummaryScreen();
        tenantResSummaryScreen.setLoginBean(lb);
        tenantResSummaryScreen.setUser(user);
        tenantResSummaryScreen.start(mainStage);

    }

    /**
     * Listener for Manager summary Button
     *
     * @throws Exception
     */
    @FXML
    public void handleManagerButton() throws Exception {
        /* system shows all reservations for the logged manager
        * So he can approve or decline them */
        ManagerResSummaryScreen managerResSummaryScreen = new ManagerResSummaryScreen();
        managerResSummaryScreen.setLoginBean(lb);
        managerResSummaryScreen.setUser(user);
        managerResSummaryScreen.start(mainStage);
    }

    /**
     * Listener for reservation Button. System shows all locations.
     *
     * @throws Exception
     */
    @FXML
    public void handleNewReservation() throws Exception {
        /* switch on LocationScreen where system shows all location */
        LocationListScreen locationListScreen = new LocationListScreen();
        locationListScreen.setLoginBean(lb);
        locationListScreen.setUser(user);

        locationListScreen.start(mainStage);
    }

    public Button getTenantButton() {
        return tenantButton;
    }

    public Button getManagerButton() {
        return managerButton;
    }

    public Button getNewReservationButton() {
        return newReservationButton;
    }

    public Text getUserFirstname() {
        return userFirstname;
    }

    public Text getUserLastname() {
        return userLastname;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public void setUser(Person user) {
        this.user = user;
    }

    public void setLb(LoginBean lb) {
        this.lb = lb;
    }


}
