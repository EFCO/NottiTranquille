package it.ispw.efco.nottitranquille.controller;

import it.ispw.efco.nottitranquille.model.Person;
import it.ispw.efco.nottitranquille.view.LoginBean;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 * Created by emanuele on 16/07/16.
 */
public class LocationListControllerFX {

    private Stage mainStage;

    private Person user;

    private LoginBean bean;

    /**
     * Default constructor
     */
    public LocationListControllerFX() {
    }

    public void handleList() {

        // TODO: 16/07/16
    }

    public Stage getMainStage() {
        return mainStage;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public Person getUser() {
        return user;
    }

    public void setUser(Person user) {
        this.user = user;
    }

    public LoginBean getBean() {
        return bean;
    }

    public void setBean(LoginBean bean) {
        this.bean = bean;
    }
}
