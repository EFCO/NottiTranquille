package it.ispw.efco.nottitranquille.controller;

import it.ispw.efco.nottitranquille.model.Address;
import it.ispw.efco.nottitranquille.model.Location;
import it.ispw.efco.nottitranquille.model.Person;
import it.ispw.efco.nottitranquille.model.dao.LocationDAO;
import it.ispw.efco.nottitranquille.view.AccessForm;
import it.ispw.efco.nottitranquille.view.LocationBean;
import it.ispw.efco.nottitranquille.view.LoginBean;
import it.ispw.efco.nottitranquille.view.ReservationScreen;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by emanuele on 18/07/16.
 */
public class LocationListControllerFX {

    private Stage mainStage;

    private Person user;

    private LoginBean loginBean;

    @FXML
    private TableView<Location> locationTableView;

    @FXML
    private MenuItem bClose;

    @FXML
    private MenuItem bShow;

    public void init() {

        // find all location
        List<Location> locations = LocationDAO.findAllLocation();

        /* Set TableView with all locations information */
        ObservableList<Location> rows = FXCollections.observableList(locations);
        locationTableView.setItems(rows);
        TableColumn<Location, String> nameCol = new TableColumn<Location, String>("Name");

        nameCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Location, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Location, String> p) {
                return new ReadOnlyObjectWrapper<String>(p.getValue().getName());
            }
        });

        TableColumn<Location, String> priceCol = new TableColumn<Location, String>("Price");
        priceCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Location, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Location, String> p) {
                return new ReadOnlyObjectWrapper<String>(Float.toString(p.getValue().getPrice()));
            }
        });

        TableColumn<Location, String> addrCol = new TableColumn<Location, String>("Address");
        addrCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Location, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Location, String> p) {

                Address address = p.getValue().getStructure().getAddress();
                String addr = address.getCity() + " " + address.getAddress() + " " + address.getPostalcode();
                return new ReadOnlyObjectWrapper<String>(addr);
            }
        });


        // Set column
        locationTableView.getColumns().setAll(nameCol, priceCol, addrCol);

        //Listener for row selection
        locationTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {

            public void handle(MouseEvent mouseEvent) {
                bShow.setDisable(false);
            }
        });

    }

    /**
     * Listener for Show Button click
     *
     * @throws Exception
     */
    @FXML
    public void show() throws Exception {
        /* switch on Reservation screen */
        ReservationScreen reservationScreen = new ReservationScreen();
        reservationScreen.setUser(user);
        reservationScreen.setLoginBean(loginBean);

        // Create LocationBean passing it to Reservation Screen
        LocationBean locationBean = new LocationBean();
        locationBean.populate(locationTableView.getSelectionModel().getSelectedItem());

        reservationScreen.setLocationBean(locationBean);

        reservationScreen.start(mainStage);
    }

    /**
     * Listener for Button Close on the Menu
     * @throws Exception
     */
    @FXML
    public void close() throws Exception {
        /* Switch on Accesso Form */
        loginBean.setLogged(false);
        AccessForm accessForm = new AccessForm();
        accessForm.start(mainStage);
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public void setUser(Person user) {
        this.user = user;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }
}


