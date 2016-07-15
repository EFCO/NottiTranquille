package it.ispw.efco.nottitranquille.view;

import it.ispw.efco.nottitranquille.controller.SingleWindowsLogged;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public abstract class LoggedApplication extends Application {

    private Stage stage;

    public void logout() {
        try {
            SingleWindowsLogged.logout();
            AccessForm accessForm = new AccessForm();
            accessForm.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setStage(Stage stage) {
        this.stage = stage;

        // Does logout if stage is closed
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                logout();
            }
        });
    }

    @Override
    public void start(Stage stage) throws Exception {
        setStage(stage);
    }
}