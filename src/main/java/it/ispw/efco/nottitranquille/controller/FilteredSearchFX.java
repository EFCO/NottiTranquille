package it.ispw.efco.nottitranquille.controller;

import it.ispw.efco.nottitranquille.model.Person;
import it.ispw.efco.nottitranquille.model.DAO.RequestDAO;
import it.ispw.efco.nottitranquille.model.Request;
import it.ispw.efco.nottitranquille.model.enumeration.RequestStatus;
import it.ispw.efco.nottitranquille.view.ScoutFilteredSearchForm;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * JavaFX controller for {@link ScoutFilteredSearchForm} for the {@link ApproveInsertRequest} use case.
 */
public class FilteredSearchFX extends SingleWindow implements Initializable {
    @FXML
    public TextField postalCodeTextField;
    @FXML
    public ComboBox<String> statusComboBox;
    @FXML
    public TextField addressTextField;
    @FXML
    public TextField structureNameTextField;
    @FXML
    public TextField requestPersonTextField;
    @FXML
    private Button backButton;
    @FXML
    private TextField nationTextField;
    @FXML
    private TextField cityTextField;
    @FXML
    public TableView<Request> requestTableView;
    @FXML
    public Button filterButton;
    @FXML
    public ProgressIndicator progressIndicator;

    /**
     * Handler for filter result of {@link Request}.
     */
    @FXML
    public void filterHandler() {
        final RequestDAO requestDAO = new RequestDAO();

        // Shows progress indicator
        progressIndicator.setVisible(true);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                // Transforms status string into RequestStatus Enum
                RequestStatus requestStatus = RequestStatus.TO_BE_REVIEWED;
                String statusString = statusComboBox.getValue();

                switch (statusString) {
                    case "Accepted":
                        requestStatus = RequestStatus.ACCEPTED;
                        break;
                    case "Rejected":
                        requestStatus = RequestStatus.REJECTED;
                        break;
                    case "To be reviewed":
                        requestStatus = RequestStatus.TO_BE_REVIEWED;
                        break;
                }

                // Retrieves requests filtered
                final List<Request> results = requestDAO.retrieveRequestsByFilter(addressTextField.getText(), cityTextField.getText(), postalCodeTextField.getText(), nationTextField.getText(), structureNameTextField.getText(), requestPersonTextField.getText(), requestStatus);

                // Populates table with requests
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {

                        ObservableList<Request> rows = FXCollections.observableList(results);

                        TableColumn<Request, String> nameColumn = new TableColumn<>("Name");
                        nameColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Request, String>, ObservableValue<String>>() {
                            public ObservableValue<String> call(TableColumn.CellDataFeatures<Request, String> p) {
                                return new ReadOnlyObjectWrapper<>(p.getValue().getStructure().getName());
                            }
                        });

                        TableColumn<Request, String> addressColumn = new TableColumn<>("Address");
                        addressColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Request, String>, ObservableValue<String>>() {
                            public ObservableValue<String> call(TableColumn.CellDataFeatures<Request, String> p) {
                                return new ReadOnlyObjectWrapper<>(p.getValue().getStructure().getAddress().getAddress());
                            }
                        });

                        TableColumn<Request, String> statusColumn = new TableColumn<>("Status");
                        statusColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Request, String>, ObservableValue<String>>() {
                            public ObservableValue<String> call(TableColumn.CellDataFeatures<Request, String> p) {
                                return new ReadOnlyObjectWrapper<>(p.getValue().getStatus().getText());
                            }
                        });

                        TableColumn<Request, String> requestByColumn = new TableColumn<>("Requested by");
                        requestByColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Request, String>, ObservableValue<String>>() {
                            public ObservableValue<String> call(TableColumn.CellDataFeatures<Request, String> p) {
                                return new ReadOnlyObjectWrapper<>(p.getValue().getRequestPerson().getFullName());
                            }
                        });

                        TableColumn<Request, String> manegedByColumn = new TableColumn<>("Managed by");
                        manegedByColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Request, String>, ObservableValue<String>>() {
                            public ObservableValue<String> call(TableColumn.CellDataFeatures<Request, String> p) {
                                return new ReadOnlyObjectWrapper<>(p.getValue().getStructure().getManagePerson().getFullName());
                            }
                        });

                        TableColumn<Request, String> requestDateColumn = new TableColumn<>("Request date");
                        requestDateColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Request, String>, ObservableValue<String>>() {
                            public ObservableValue<String> call(TableColumn.CellDataFeatures<Request, String> p) {
                                return new ReadOnlyObjectWrapper<>(DateTimeFormat.forPattern("dd/MM/yyyy").print(p.getValue().getRequestDate()));
                            }
                        });

                        TableColumn<Request, String> acceptedDateColumn = new TableColumn<>("Accepted date");
                        acceptedDateColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Request, String>, ObservableValue<String>>() {
                            public ObservableValue<String> call(TableColumn.CellDataFeatures<Request, String> p) {
                                DateTime acceptedDateTime = p.getValue().getAcceptedDate();

                                String dateString;
                                if (acceptedDateTime == null) {
                                    dateString = "---";
                                } else {
                                    dateString = DateTimeFormat.forPattern("dd/MM/yyyy").print(acceptedDateTime);
                                }

                                return new ReadOnlyObjectWrapper<>(dateString);
                            }
                        });

                        TableColumn<Request, String> reviewedByColumn = new TableColumn<>("Review by");
                        reviewedByColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Request, String>, ObservableValue<String>>() {
                            public ObservableValue<String> call(TableColumn.CellDataFeatures<Request, String> p) {
                                Person reviewerPerson = p.getValue().getReviewerPerson();

                                String reviewedByString;
                                if (reviewerPerson == null) {
                                    reviewedByString = "---";
                                } else {
                                    reviewedByString = reviewerPerson.getFullName();
                                }
                                return new ReadOnlyObjectWrapper<>(reviewedByString);
                            }
                        });

                        requestTableView.setItems(rows);
                        requestTableView.getColumns().setAll(nameColumn, statusColumn, addressColumn, manegedByColumn, requestDateColumn, requestByColumn, acceptedDateColumn, reviewedByColumn);
                        requestTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

                        // Sets empty message
                        if (rows.size() == 0) {
                            requestTableView.setPlaceholder(new Label("There are no requests to show!"));
                        }
                        // Hides progress indicator
                        progressIndicator.setVisible(false);
                    }
                });
            }
        });

        thread.start();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Initializes combo box with RequestStatus
        List<String> statusList = new ArrayList<>();
        for (RequestStatus requestStatus : RequestStatus.values()) {
            statusList.add(requestStatus.getText());
        }

        statusComboBox.setItems(FXCollections.observableList(statusList));
        statusComboBox.setValue(RequestStatus.TO_BE_REVIEWED.getText());
    }
}
