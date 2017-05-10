package Events;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import org.controlsfx.control.CheckComboBox;

/**
 * Created by Aurelian on 5/10/2017.
 */

public class AddEvent {

    private CheckComboBox<String> checkComboBox;
    @FXML Button okButton;
    @FXML HBox reviewersHBox;

    public AddEvent() {

    }

    @FXML
    public void initialize() {
        // TODO replace example reviewers below with real reviewers from DB
        ObservableList<String> reviewers = FXCollections.observableArrayList();
        reviewers.add("Horia");
        reviewers.add("Closca");
        reviewers.add("Crisan");
        checkComboBox = new CheckComboBox<String>(reviewers);
        checkComboBox.setPrefWidth(3000);

        reviewersHBox.getChildren().add(checkComboBox);

    }
}
