package it.ispw.efco.nottitranquille.controller;

import it.ispw.efco.nottitranquille.model.*;
import it.ispw.efco.nottitranquille.model.enumeration.ReservationState;
import it.ispw.efco.nottitranquille.view.AccessForm;
import it.ispw.efco.nottitranquille.view.LoginBean;
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
public class ManagerResSummaryFX {

    private Stage mainStage;

    private Person user;

    private LoginBean loginBean;

    public MenuItem bClose;
    public MenuItem bDecline;
    public MenuItem bAccept;

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

            Manager manager = (Manager) user.getRole(Manager.class.getSimpleName());
            reservations = manager.getToApprove();

        } catch (Exception e) {
            e.printStackTrace();
        }

        ObservableList<Reservation> rows = FXCollections.observableList(reservations);
        reservationTableView.setItems(rows);
        TableColumn<Reservation, String> nameCol = new TableColumn<Reservation, String>("Address");

        nameCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Reservation, String> p) {
                Address address = p.getValue().getLocation().getStructure().getAddress();
                String addr = address.getCity() + " " + address.getAddress() + " " + address.getPostalcode();
                return new ReadOnlyObjectWrapper<String>(addr);
            }
        });

        TableColumn<Reservation, String> surnameCol = new TableColumn<Reservation, String>("Price");
        surnameCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Reservation, String> p) {
                return new ReadOnlyObjectWrapper<String>(Float.toString(p.getValue().getPrice()));
            }
        });

        TableColumn<Reservation, String> roleCol = new TableColumn<Reservation, String>("Tenant");
        roleCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Reservation, String> p) {

                return new ReadOnlyObjectWrapper<String>(p.getValue().getTenant().getfirstname() + " "
                        + p.getValue().getTenant().getlastname());
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

        reservationTableView.getColumns().setAll(nameCol, surnameCol, roleCol, stateCol, dateCol);

        reservationTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {

            public void handle(MouseEvent mouseEvent) {
                if (reservationTableView.getSelectionModel().getSelectedItem().getState()
                        != ReservationState.Declined && reservationTableView.getSelectionModel().getSelectedItem().getState()
                        != ReservationState.ToPay && reservationTableView.getSelectionModel().getSelectedItem().getState()
                        != ReservationState.Paid) {

                    bAccept.setDisable(false);
                    bDecline.setDisable(false);
                } else {
                    bAccept.setDisable(true);
                    bDecline.setDisable(true);
                }
            }
        });

    }

    @FXML
    public void decline() {
        ReservationController controller = ReservationController.getInstance();
        controller.declineReservation(reservationTableView.getSelectionModel().getSelectedItem().getId());
        init();
    }

    @FXML
    public void accept() {
        ReservationController controller = ReservationController.getInstance();
        controller.approveReservation(reservationTableView.getSelectionModel().getSelectedItem().getId());
        init();
    }

    @FXML
    public void close() throws Exception {
        loginBean.setLogged(false);
        AccessForm accessForm = new AccessForm();
        accessForm.start(mainStage);
    }


}
