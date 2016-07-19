package it.ispw.efco.nottitranquille.view;

import it.ispw.efco.nottitranquille.controller.FilteredSearch;
import it.ispw.efco.nottitranquille.controller.FilteredSearchFX;
import it.ispw.efco.nottitranquille.model.Request;
import it.ispw.efco.nottitranquille.model.enumeration.RequestStatus;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FilteredSearch Boundary.
 * <br>
 * This Use Case shows the process by which a Scout can view a list of {@link Request} of a {@link it.ispw.efco.nottitranquille.model.Structure}.
 *
 * @see FilteredSearch
 * @see Request
 * @see RequestStatus
 */
public class ScoutFilteredSearchForm extends LoggedApplication {

    @Override
    public void start(Stage stage) throws Exception {
        super.start(stage);

        final FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/scoutFilteredSearchForm.fxml"));

        Parent root = (Parent) loader.load();
        stage.setScene(new Scene(root));
        stage.show();

        // Centers to screen
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);

        final FilteredSearchFX controller = loader.getController();
        controller.setMainStage(stage);
        controller.setPrevious(EmployeeMainScreen.class);

        controller.filterHandler();

        // Sets selection mode of table view
        controller.requestTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        // Sets on click event on row in order to open detail form
        controller.requestTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                Request request = controller.requestTableView.getSelectionModel().getSelectedItem();
                if (request != null) {
                    ScoutApproveInsertRequestForm scoutApproveInsertRequestForm = new ScoutApproveInsertRequestForm();
                    // Passes request
                    scoutApproveInsertRequestForm.setRequest(request);
                    try {
                        scoutApproveInsertRequestForm.start(controller.getMainStage());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}