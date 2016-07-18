package it.ispw.efco.nottitranquille.controller;

import it.ispw.efco.nottitranquille.view.EmployeeMainScreen;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 * JavaFX controller that manage a single windowed {@link Application}.
 *
 * @see Application
 */
public abstract class SingleWindow {

    /**
     * Main {@link Stage} to pass in order to overwrite with new {@link Application}.
     */
    private Stage mainStage;

    /**
     * Previous {@link Stage} used in order to go back.
     */
    private Class<? extends Application> previous;

    /**
     * Sets the Main{@link Stage}.
     *
     * @param stage the stage of
     */
    public void setMainStage(Stage stage) {
        this.mainStage = stage;
    }

    /**
     * Gets the Main{@link Stage}.
     *
     * @return the main stage
     */
    public Stage getMainStage() {
        return mainStage;
    }

    /**
     * Sets previous {@link Application} in order to go back.
     *
     * @param previous
     */
    public void setPrevious(Class<? extends Application> previous) {
        this.previous = previous;
    }

    /**
     * Handler for back {@link javafx.scene.control.Button}.
     */
    @FXML
    protected void backHandler() {
        if (previous == null) {
            previous = EmployeeMainScreen.class;
        }
        try {
            previous.newInstance().start(getMainStage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}