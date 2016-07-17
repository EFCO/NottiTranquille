package it.ispw.efco.nottitranquille.view;

import it.ispw.efco.nottitranquille.controller.TenantResSummaryFX;
import it.ispw.efco.nottitranquille.model.Person;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by emanuele on 16/07/16.
 */
public class TenantResSummaryScreen extends Application {

    private Person user;

    private LoginBean loginBean;


    public TenantResSummaryScreen() {

    }

    public void setUser(Person user) {
        this.user = user;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/fxml/TenantResListScreen.fxml"));

        Parent root = (Parent) loader.load();
        stage.setScene(new Scene(root));
        stage.show();

        TenantResSummaryFX controller = loader.getController();

        controller.bDelete.setDisable(false);
        controller.setUser(user);
        controller.setLoginBean(loginBean);

        controller.init();
        controller.setMainStage(stage);
    }
}
