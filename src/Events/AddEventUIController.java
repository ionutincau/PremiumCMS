package Events;

import domain.Event;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
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

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * Created by Aurelian on 5/10/2017.
 */
public class AddEventUIController implements Initializable{
    private CheckComboBox<String> checkComboBox;
    private EventsController controller;
    private Event event;
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
    public void initialize(URL fxmlFileLocation, ResourceBundle resourceBundle)
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
    public void initData(String name, Event event, EventsController controller)
    {
        this.controller=controller;
        this.event=event;
        okButton.setText(name);
        if (name=="Adauga")
        {
            okButton.setOnAction(e->Add());
        }
        else
        {
            abstractDeadlineDatePicker.setValue(event.getD_abstract().toLocalDate());
            endDataDatePicker.setValue(event.getEnd().toLocalDate());
            proposalDeadlineDatePicker.setValue(event.getD_proposal().toLocalDate());
            taxDeadlineDatePicker.setValue(event.getD_taxes().toLocalDate());
            nameTextField.setText(event.getName());
            descriptionTextArea.setText(event.getDescription());
            websiteTextField.setText(event.getWeb_page());
            locationTextField.setText(event.getLocation());
            evaluationDeadlineDatePicker.setValue(event.getD_evaluation().toLocalDate());
            startDateDatePicker.setValue(event.getStart().toLocalDate());
            okButton.setOnAction(e->Edit());
        }
    }
    public void Add()
    {
        try {

            LocalDate localDateOut = endDataDatePicker.getValue();
            Date Enddate = java.sql.Date.valueOf(localDateOut);
            LocalDate localDateIn = startDateDatePicker.getValue();
            Date Startdate = java.sql.Date.valueOf(localDateIn);
            LocalDate abstractDate = abstractDeadlineDatePicker.getValue();
            Date  AbstractDate= java.sql.Date.valueOf(abstractDate);
            LocalDate proposalDate = proposalDeadlineDatePicker.getValue();
            Date  Proposaldate= java.sql.Date.valueOf(proposalDate);
            LocalDate evaluationDate = evaluationDeadlineDatePicker.getValue();
            Date  Evaluationdate= java.sql.Date.valueOf(evaluationDate);
            LocalDate  taxDate= taxDeadlineDatePicker.getValue();
            Date  Taxdate= java.sql.Date.valueOf(taxDate);
            controller.add(nameTextField.getText(),Startdate,Enddate,websiteTextField.getText(),locationTextField.getText(),descriptionTextArea.getText(),AbstractDate,Proposaldate,Evaluationdate,Taxdate);
            Stage stage = (Stage)okButton.getScene().getWindow();
            stage.close();
        }
        catch (NumberFormatException e) {
            //UtilFunctions.showInfo("Format invalid!\n");
        }
        catch (Exception e) {
            //UtilFunctions.showInfo(e.getMessage());
        }
    }
    public void Edit()
    {
        try {
            LocalDate localDateOut = endDataDatePicker.getValue();
            Date Enddate = java.sql.Date.valueOf(localDateOut);
            LocalDate localDateIn = startDateDatePicker.getValue();
            Date Startdate = java.sql.Date.valueOf(localDateIn);
            LocalDate abstractDate = abstractDeadlineDatePicker.getValue();
            Date  AbstractDate= java.sql.Date.valueOf(abstractDate);
            LocalDate proposalDate = proposalDeadlineDatePicker.getValue();
            Date  Proposaldate= java.sql.Date.valueOf(proposalDate);
            LocalDate evaluationDate = evaluationDeadlineDatePicker.getValue();
            Date  Evaluationdate= java.sql.Date.valueOf(evaluationDate);
            LocalDate  taxDate= taxDeadlineDatePicker.getValue();
            Date  Taxdate= java.sql.Date.valueOf(taxDate);


            controller.edit(event.getId_event(),nameTextField.getText(),Startdate,Enddate,websiteTextField.getText(),locationTextField.getText(),descriptionTextArea.getText(),AbstractDate,Proposaldate,Evaluationdate,Taxdate);
            Stage stage = (Stage)okButton.getScene().getWindow();
            stage.close();
        }
        catch (NumberFormatException e) {
            //UtilFunctions.showInfo("Format invalid!\n");
        }
        catch (Exception e) {
            //UtilFunctions.showInfo(e.getMessage());
        }
    }
}