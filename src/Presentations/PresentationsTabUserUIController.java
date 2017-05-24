package Presentations;

import domain.Presentation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.util.Callback;

import java.net.URL;
import java.util.List;
import java.util.Observer;
import java.util.ResourceBundle;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

/**
 * Created by Aurelian on 5/23/2017.
 */
public class PresentationsTabUserUIController implements Observer, Initializable {
    PresentationsController presentationsController = new PresentationsController();
    static String selected = "";

    @FXML
    private Button presentationParticipateButton;

    @FXML
    private ListView<String> presentationsListView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Presentation> presentations = presentationsController.getAllPresentations();
        for (Presentation presentation : presentations) {
            presentationsListView.getItems().addAll(presentation.toString());
        }
        setPresentationParticipateButtonListener();
    }

    @Override
    public void update(java.util.Observable o, Object arg) {

    }

    public void setPresentationParticipateButtonListener() {
        presentationParticipateButton.setOnAction(event -> {
            selected = presentationsListView.getSelectionModel().getSelectedItems().toString();
            presentationsListView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
                public ListCell<String> call(ListView<String> presentationsListView) {
                    return new PresentationsTabUserUIController.ColorRectCell();
                }
            });
        });
    }

    static class ColorRectCell extends ListCell<String> {
        PresentationsController presentationsController = new PresentationsController();
        List<Presentation> presentations = presentationsController.getAllPresentations();

        @Override
        public void updateItem(String item, boolean empty) {
            this.setContentDisplay(ContentDisplay.CENTER);
            super.updateItem(item, empty);
            javafx.scene.shape.Rectangle rect = new javafx.scene.shape.Rectangle(600, 20);
            if (item != null) {
                rect.setFill(Color.web("green"));
                setGraphic(rect);
            }
            else {
                this.setGraphic((Node)null);
            }
            setText(empty ? null : item.toString());
        }
    }
}


