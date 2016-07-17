package it.ispw.efco.nottitranquille.controller;

import it.ispw.efco.nottitranquille.model.Manager;
import it.ispw.efco.nottitranquille.model.Person;
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
    private Text userFirstname;

    @FXML
    private Text userLastname;

    public UserPageControllerFX() {
    }

    @FXML
    public void handleTenantButton() throws Exception {
        TenantResSummaryScreen tenantResSummaryScreen = new TenantResSummaryScreen();
        tenantResSummaryScreen.setLoginBean(lb);
        tenantResSummaryScreen.setUser(user);
        tenantResSummaryScreen.start(mainStage);

    }

    @FXML
    public void handleManagerButton() throws Exception {
        ManagerResSummaryScreen managerResSummaryScreen = new ManagerResSummaryScreen();
        managerResSummaryScreen.setLoginBean(lb);
        managerResSummaryScreen.setUser(user);
        managerResSummaryScreen.start(mainStage);
    }

    public Button getTenantButton() {
        return tenantButton;
    }

    public Button getManagerButton() {
        return managerButton;
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
