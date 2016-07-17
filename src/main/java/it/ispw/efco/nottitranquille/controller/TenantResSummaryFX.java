package it.ispw.efco.nottitranquille.controller;

import it.ispw.efco.nottitranquille.model.Address;
import it.ispw.efco.nottitranquille.model.Person;
import it.ispw.efco.nottitranquille.model.Reservation;
import it.ispw.efco.nottitranquille.model.Tenant;
import it.ispw.efco.nottitranquille.model.enumeration.ReservationState;
import it.ispw.efco.nottitranquille.view.AccessForm;
import it.ispw.efco.nottitranquille.view.ListReservationBean;
import it.ispw.efco.nottitranquille.view.LoginBean;
import it.ispw.efco.nottitranquille.view.PaymentFormScreen;
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
 * Created by emanuele on 16/07/16.
 */
public class TenantResSummaryFX {

    private Stage mainStage;

    private Person user;

    private LoginBean loginBean;

    @FXML
    public MenuItem bClose;

    @FXML
    public MenuItem bDelete;

    @FXML
    public MenuItem bPay;

    public TableView<Reservation> reservationTableView;


    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public void setUser(Person user) {
        this.user = user;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }


    public void init() {

        List<Reservation> reservations = new ArrayList<Reservation>();
        try {
            Tenant tenant = (Tenant) user.getRole(Tenant.class.getSimpleName());
            reservations = tenant.getReservations();

        } catch (Exception e) {
            e.printStackTrace();
        }

        ObservableList<Reservation> rows = FXCollections.observableList(reservations);
        reservationTableView.setItems(rows);
        TableColumn<Reservation, String> nameCol = new TableColumn<Reservation, String>("Name");

        nameCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Reservation, String> p) {
                return new ReadOnlyObjectWrapper<String>(p.getValue().getLocation().getName());
            }
        });

        TableColumn<Reservation, String> priceCol = new TableColumn<Reservation, String>("Price");
        priceCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Reservation, String> p) {
                return new ReadOnlyObjectWrapper<String>(Float.toString(p.getValue().getPrice()));
            }
        });

        TableColumn<Reservation, String> addrCol = new TableColumn<Reservation, String>("Address");
        addrCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Reservation, String> p) {

                Address address = p.getValue().getLocation().getStructure().getAddress();
                String addr = address.getCity() + " " + address.getAddress() + " " + address.getPostalcode();
                return new ReadOnlyObjectWrapper<String>(addr);
            }
        });

        TableColumn<Reservation, String> stateCol = new TableColumn<Reservation, String>("State");
        stateCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Reservation, String> p) {

                return new ReadOnlyObjectWrapper<String>(p.getValue().getState().getText());
            }
        });

        TableColumn<Reservation, String> dateCol = new TableColumn<Reservation, String>("period");
        dateCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Reservation, String> p) {

                return new ReadOnlyObjectWrapper<String>(
                        String.format("%d/%d/%d %d/%d/%d", p.getValue().getStartDate().getDayOfMonth(),
                                p.getValue().getStartDate().getMonthOfYear(),
                                p.getValue().getStartDate().getYear(),
                                p.getValue().getEndDate().getDayOfMonth(),
                                p.getValue().getEndDate().getMonthOfYear(),
                                p.getValue().getEndDate().getYear()));
            }
        });

        reservationTableView.getColumns().setAll(nameCol, priceCol, addrCol, stateCol, dateCol);

        reservationTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {

            public void handle(MouseEvent mouseEvent) {
                if (reservationTableView.getSelectionModel().getSelectedItem().getState()
                        != ReservationState.Paid) {
                    bDelete.setDisable(false);
                } else
                    bDelete.setDisable(true);

                if (reservationTableView.getSelectionModel().getSelectedItem().getState()
                        == ReservationState.ToPay) {
                    bPay.setDisable(false);
                } else
                    bPay.setDisable(true);

            }
        });

    }

    @FXML
    public void pay() throws Exception {
        PaymentFormScreen paymentFormScreen = new PaymentFormScreen();
        paymentFormScreen.setReservation(reservationTableView.getSelectionModel().getSelectedItem());
        paymentFormScreen.setUser(user);
        paymentFormScreen.setLoginBean(loginBean);

        paymentFormScreen.start(mainStage);
    }

    @FXML
    public void removeReservation() {
        ReservationController controller = ReservationController.getInstance();
        controller.remove(reservationTableView.getSelectionModel().getSelectedItem().getId());
        init();
    }

    @FXML
    public void close() throws Exception {
        loginBean.setLogged(false);
        AccessForm accessForm = new AccessForm();
        accessForm.start(mainStage);
    }


}
