package it.ispw.efco.nottitranquille.view;

import it.ispw.efco.nottitranquille.controller.SingleWindowsLogged;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Simple implementation of a Logged{@link Application} that supports logout on close.
 */
public abstract class LoggedApplication extends Application {

    /**
     * The main {@link Stage}
     */
    private Stage stage;

    /**
     * Logout and comes back to {@link UserAccessForm}.
     */
    private void logout() {
        try {
            SingleWindowsLogged.logout();
            UserAccessForm userAccessForm = new UserAccessForm();
            userAccessForm.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets {@link Stage} of application.
     *
     * @param stage the Stage to set
     */
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
        // Sets stage in every start() calling super.start(stage)!
        setStage(stage);
    }
}