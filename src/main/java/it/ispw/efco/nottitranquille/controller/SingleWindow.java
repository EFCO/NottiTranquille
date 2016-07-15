package it.ispw.efco.nottitranquille.controller;

import javafx.stage.Stage;

public abstract class SingleWindow {

    private Stage mainStage;

    public void setMainStage(Stage stage) {
        this.mainStage = stage;
    }

    public Stage getMainStage() {
        return mainStage;
    }
}