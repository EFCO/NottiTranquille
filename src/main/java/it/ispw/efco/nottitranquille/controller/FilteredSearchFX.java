package it.ispw.efco.nottitranquille.controller;

import it.ispw.efco.nottitranquille.model.dao.RequestDAO;
import it.ispw.efco.nottitranquille.model.Request;
import it.ispw.efco.nottitranquille.model.enumeration.RequestStatus;
import it.ispw.efco.nottitranquille.view.MainScreen;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.List;

public class FilteredSearchFX extends SingleWindow {
    @FXML
    private Button backButton;

    @FXML
    private TextField address;

    @FXML
    private TextField nation;

    @FXML
    private TextField city;

    @FXML
    private MenuButton status;

    @FXML
    private TableView<Request> tableview;

    public FilteredSearchFX() {
    }

    public void setStatusMenuButton() {
        for (RequestStatus state : RequestStatus.values()) {
            CheckMenuItem menuItem = new CheckMenuItem(state.getText());
            this.status.getItems().add(menuItem);
        }

        status.setText(status.getItems().get(0).getText());
    }

    @FXML
    protected void backHandler() {
        MainScreen mainScreen = new MainScreen();
        try {
            mainScreen.start(getMainStage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void handleSearchButtonAction() {
        RequestDAO requestDAO = new RequestDAO();

        List<Request> results = requestDAO.selectAllRequestsByFilter(nation.getText(),city.getText(), RequestStatus.valueOf("Accepted"));
        ObservableList rows = FXCollections.observableList(results);
        tableview.setItems(rows);
        TableColumn<Request,String> nameCol = new TableColumn<Request,String>("Name");
        nameCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Request, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Request, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getStructure().getName());
            }
        });
        TableColumn<Request,String> addressCol = new TableColumn<Request,String>("Address");
        addressCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Request, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Request, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getStructure().getStructureAddress().getAddress());
            }
        });
        TableColumn<Request,String> statusCol = new TableColumn<Request,String>("Status");
        statusCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Request, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Request, String> p) {
                return new ReadOnlyObjectWrapper(p.getValue().getStatus().toString());
            }
        });
        TableColumn<Request,String> requestedByCol = new TableColumn<Request,String>("RequestedBy");
        requestedByCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Request, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Request, String> p) {
                //TODO to implement
                return new ReadOnlyObjectWrapper(p.getValue().getStatus().toString());
            }
        });
        TableColumn<Request,String> managedByCol = new TableColumn<Request,String>("ManagedBy");
        managedByCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Request, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Request, String> p) {
                //TODO to implement
                return new ReadOnlyObjectWrapper(p.getValue().getStatus().toString());
            }
        });
        tableview.getColumns().setAll(nameCol, addressCol, managedByCol, requestedByCol, statusCol);

    }
}
