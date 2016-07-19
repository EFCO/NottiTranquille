package it.ispw.efco.nottitranquille.view;

import it.ispw.efco.nottitranquille.controller.ApproveInsertRequestFX;
import it.ispw.efco.nottitranquille.model.Location;
import it.ispw.efco.nottitranquille.model.Person;
import it.ispw.efco.nottitranquille.model.Request;
import it.ispw.efco.nottitranquille.model.enumeration.RequestStatus;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

/**
 * ApproveInsertRequest Boundary.
 * <br>
 * This Use Case shows the process by which a Scout can approve (or modify) a {@link Request} of a {@link it.ispw.efco.nottitranquille.model.Structure}.
 *
 * @see it.ispw.efco.nottitranquille.controller.ApproveInsertRequest
 * @see Request
 * @see RequestStatus
 */
public class ScoutApproveInsertRequestForm extends LoggedApplication {

    /**
     * The Request
     */
    private Request request;

    @Override
    public void start(Stage stage) throws Exception {
        super.start(stage);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/approveInsertRequestForm.fxml"));

        Parent root = (Parent) loader.load();
        stage.setScene(new Scene(root));
        stage.show();

        // Centers to screen
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);

        ApproveInsertRequestFX controller = loader.getController();
        controller.setMainStage(stage);
        controller.setPrevious(ScoutFilteredSearchForm.class);
        controller.setRequest(request);

        // Populates form with data
        controller.structureImageView.setImage(new Image("file:src/main/resources/images/piscine-di-albergo-Medulin-2.jpg"));

        controller.statusComboBox.setValue(request.getStatus().getText());
        controller.structureNameLabel.setText(request.getStructure().getName());
        controller.addressLabel.setText(request.getStructure().getAddress().getAddress());

        DateTime acceptedDateTime = request.getAcceptedDate();

        String acceptedDateString;
        if (acceptedDateTime == null) {
            acceptedDateString = "---";
        } else {
            acceptedDateString = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm").print(acceptedDateTime);
        }

        controller.acceptedDateLabel.setText(acceptedDateString);
        controller.requestDateLabel.setText(DateTimeFormat.forPattern("dd/MM/yyyy HH:mm").print(request.getRequestDate()));
        controller.lastModifiedDate.setText(DateTimeFormat.forPattern("dd/MM/yyyy HH:mm").print(request.getLastModified()));

        controller.managedByLabel.setText(request.getStructure().getManagePerson().getFullName());
        controller.ownedByLabel.setText(request.getStructure().getOwnerPerson().getFullName());

        Person reviewerPerson = request.getReviewerPerson();

        String reviewedByString;
        if (reviewerPerson == null) {
            reviewedByString = "---";
        } else {
            reviewedByString = reviewerPerson.getFullName();
        }

        controller.reviewedByLabel.setText(reviewedByString);

        DateTime checkInDateTime = request.getStructure().getCheckIn();

        String checkInString;
        if (checkInDateTime == null) {
            checkInString = "---";
        } else {
            checkInString = DateTimeFormat.forPattern("HH:mm").print(checkInDateTime);
        }
        controller.checkInLabel.setText(checkInString);

        DateTime checkOutDateTime = request.getStructure().getCheckOut();

        String checkOutString;
        if (checkOutDateTime == null) {
            checkOutString = "---";
        } else {
            checkOutString = DateTimeFormat.forPattern("HH:mm").print(checkOutDateTime);
        }
        controller.checkOutLabel.setText(checkOutString);

        ObservableList<Location> rows = FXCollections.observableList(request.getStructure().getLocations());

        TableColumn<Location, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Location, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Location, String> p) {
                return new ReadOnlyObjectWrapper<>(p.getValue().getDescription());
            }
        });

        TableColumn<Location, String> numberOfRoomsColumn = new TableColumn<>("Rooms");
        numberOfRoomsColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Location, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Location, String> p) {
                return new ReadOnlyObjectWrapper<>(String.valueOf(p.getValue().getNumberOfRooms()));
            }
        });

        TableColumn<Location, String> numberOfBathroomsColumn = new TableColumn<>("Bathrooms");
        numberOfBathroomsColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Location, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Location, String> p) {
                return new ReadOnlyObjectWrapper<>(String.valueOf(p.getValue().getNumberOfBathrooms()));
            }
        });

        TableColumn<Location, String> numberOfBedsColumn = new TableColumn<>("Beds");
        numberOfBedsColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Location, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Location, String> p) {
                return new ReadOnlyObjectWrapper<>(String.valueOf(p.getValue().getNumberOfBedrooms()));
            }
        });

        TableColumn<Location, String> numberMaxGuestsColumn = new TableColumn<>("Max guests");
        numberMaxGuestsColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Location, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Location, String> p) {
                return new ReadOnlyObjectWrapper<>(String.valueOf(p.getValue().getMaxGuestsNumber()));
            }
        });

        TableColumn<Location, String> numberOfBedroomsColumn = new TableColumn<>("Bedrooms");
        numberOfBedroomsColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Location, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Location, String> p) {
                return new ReadOnlyObjectWrapper<>(String.valueOf(p.getValue().getNumberOfBedrooms()));
            }
        });

        TableColumn<Location, String> locationTypeColumn = new TableColumn<>("Type");
        locationTypeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Location, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Location, String> p) {
                return new ReadOnlyObjectWrapper<>(p.getValue().getType().getText());
            }
        });

        controller.locationsTableView.setItems(rows);
        controller.locationsTableView.getColumns().setAll(locationTypeColumn, descriptionColumn, numberOfRoomsColumn, numberOfBedroomsColumn, numberOfBathroomsColumn, numberOfBedsColumn, numberMaxGuestsColumn);
        controller.locationsTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        if (rows.size() == 0) {
            controller.locationsTableView.setPlaceholder(new Label("There are no locations to show!"));
        }
    }

    /**
     * Sets Request to Boundary
     *
     * @param request
     */
    public void setRequest(Request request) {
        this.request = request;
    }
}
