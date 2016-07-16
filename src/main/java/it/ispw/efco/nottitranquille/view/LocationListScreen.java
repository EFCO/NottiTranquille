package it.ispw.efco.nottitranquille.view;


import it.ispw.efco.nottitranquille.controller.LocationListControllerFX;
import it.ispw.efco.nottitranquille.model.Person;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by emanuele on 16/07/16.
 */
public class LocationListScreen extends Application {

    private LoginBean bean;

    private Person user;

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/fxml/LocationListForm.fxml"));

        Parent root = (Parent) loader.load();
        stage.setScene(new Scene(root));
        stage.show();

        LocationListControllerFX controller = loader.getController();

        controller.setMainStage(stage);
        controller.setBean(bean);
        controller.setUser(user);

    }

    public LoginBean getBean() {
        return bean;
    }

    public void setBean(LoginBean bean) {
        this.bean = bean;
    }

    public Person getUser() {
        return user;
    }

    public void setUser(Person user) {
        this.user = user;
    }

}
