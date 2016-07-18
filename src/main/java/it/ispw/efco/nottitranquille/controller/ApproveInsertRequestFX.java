package it.ispw.efco.nottitranquille.controller;

import it.ispw.efco.nottitranquille.model.Request;
import it.ispw.efco.nottitranquille.model.enumeration.RequestStatus;
import it.ispw.efco.nottitranquille.view.ScoutApproveInsertRequestForm;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * JavaFX controller for {@link ScoutApproveInsertRequestForm} for the {@link ApproveInsertRequest} use case.
 */
public class ApproveInsertRequestFX extends SingleWindow implements Initializable {

    @FXML
    public Button backButton;
    @FXML
    public Label structureNameLabel;
    @FXML
    public Label addressLabel;
    @FXML
    public Label requestDateLabel;
    @FXML
    public Label acceptedDateLabel;
    @FXML
    public Label lastModifiedDate;
    @FXML
    public ComboBox<String> statusComboBox;
    @FXML
    public Button saveButton;
    @FXML
    public Label reviewedByLabel;
    @FXML
    public Label requestedByLabel;
    @FXML
    public Label checkInLabel;
    @FXML
    public Label checkOutLabel;
    @FXML
    public Label ownedByLabel;
    @FXML
    public Label managedByLabel;
    @FXML
    public Label termOfServicesLabel;
    @FXML
    public Label termsOfCancellationLabel;
    @FXML
    public TableView locationsTableView;
    @FXML
    public ImageView structureImageView;

    /**
     * The {@link Request} to approve (or change status)
     */
    private Request request;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> statusList = new ArrayList<>();
        for (RequestStatus requestStatus : RequestStatus.values()) {
            statusList.add(requestStatus.getText());
        }

        statusComboBox.setItems(FXCollections.observableList(statusList));
    }

    /**
     * Handles events on save {@link Button}.
     */
    @FXML
    public void handleSave() {
        RequestStatus requestStatus = RequestStatus.TO_BE_REVIEWED;
        String statusString = statusComboBox.getValue();

        switch (statusString) {
            case "ACCEPTED":
                requestStatus = RequestStatus.ACCEPTED;
                break;
            case "REJECTED":
                requestStatus = RequestStatus.REJECTED;
                break;
            case "To be reviewed":
                requestStatus = RequestStatus.TO_BE_REVIEWED;
                break;
        }

        // Calls controller in order to change the status
        final RequestStatus finalRequestStatus = requestStatus;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                ApproveInsertRequest.changeRequestStatus(request, finalRequestStatus);
            }
        });

        thread.start();

        // Goes back
        backHandler();
    }

    /**
     * Sets the {@link Request} to handle.
     *
     * @param request the request to handle
     */
    public void setRequest(Request request) {
        this.request = request;
    }

    /**
     * Handles click on Term of Service and Term of Cancellation {@link Label}.
     */
    @FXML
    public void handleClick(Event event) {

        String message = "";

        switch (((Label) event.getSource()).getText()) {
            case "Terms of services":
                message = request.getStructure().getTermsOfService();
                break;
            case "Terms of cancellation":
                message = request.getStructure().getTermsOfCancellation();
                break;
        }

        // Opens a new Stage with right Term
        final String finalMessage = message;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Stage stage = new Stage();
                stage.setScene(new Scene(new Label(finalMessage)));
                stage.show();
            }
        });

    }
}
