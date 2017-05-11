package Events;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import org.controlsfx.control.CheckComboBox;

/**
 * Created by Aurelian on 5/10/2017.
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import org.controlsfx.control.CheckComboBox;

/**
 * Created by Aurelian on 5/10/2017.
 */
public class AddEventUIController {
    private CheckComboBox<String> checkComboBox;

    @FXML
    private DatePicker abstractDeadlineDatePicker;

    @FXML
    private DatePicker endDataDatePicker;

    @FXML
    private DatePicker proposalDeadlineDatePicker;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private Button okButton;

    @FXML
    private DatePicker taxDeadlineDatePicker;

    @FXML
    private TextField websiteTextField;

    @FXML
    private TextField locationTextField;

    @FXML
    private DatePicker evaluationDeadlineDatePicker;

    @FXML
    private DatePicker startDateDatePicker;

    @FXML
    private HBox sessionsHBox;

    public AddEventUIController()
    {

    }

    @FXML
    public void initialize()
    {
        // TODO replace example sessionsList below with real sessionsList from DB
        ObservableList<String> sessionsList = FXCollections.observableArrayList();
        sessionsList.add("Horia");
        sessionsList.add("Closca");
        sessionsList.add("Crisan");
        checkComboBox = new CheckComboBox<String>(sessionsList);
        checkComboBox.setPrefWidth(3000);

        sessionsHBox.getChildren().add(checkComboBox);

    }
}