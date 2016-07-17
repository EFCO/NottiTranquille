package it.ispw.efco.nottitranquille.view;

import it.ispw.efco.nottitranquille.controller.ManagerResSummaryFX;
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
public class ManagerResSummaryScreen extends Application {

    private Person user;

    private LoginBean loginBean;


    public ManagerResSummaryScreen() {

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
                "/fxml/ManagerResListScreen.fxml"));

        Parent root = (Parent) loader.load();
        stage.setScene(new Scene(root));
        stage.show();

        ManagerResSummaryFX controller = loader.getController();

        controller.setUser(user);
        controller.setLoginBean(loginBean);

        controller.init();
        controller.setMainStage(stage);
    }
}
