package Sessions;

import domain.Sesiune;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;

/**
 * Created by Aurelian on 5/11/2017.
 */
public class AddSessionUIController implements Initializable{

    @FXML
    private ChoiceBox<?> sessionPresidentChoiceBox;
    @FXML
    private Button buttonOkId;
    @FXML
    private DatePicker sessionDateOutDatePicker;

    @FXML
    private ChoiceBox<?> sessionRoomChoiceBox;

    @FXML
    private DatePicker sessionDateInDatePicker;

    @FXML
    private TextField sessionNameTextField;
    private SesiuneController controller;
    private Sesiune sesiune;
    public AddSessionUIController()
    {}
    public void initialize(URL fxmlFileLocation, ResourceBundle resourceBundle)
    {}
    public void initData(String name, Sesiune sesiune,SesiuneController controller)
    {
        this.controller=controller;
        this.sesiune=sesiune;
        sessionPresidentChoiceBox.setVisible(false);
        sessionRoomChoiceBox.setVisible(false);
        sessionDateInDatePicker.setVisible(false);
        sessionDateOutDatePicker.setVisible(false);
        buttonOkId.setText(name);
        if (name=="Adauga")
        {
            buttonOkId.setOnAction(e->Add());
        }
        else
        {

            sessionPresidentChoiceBox.setVisible(true);
            sessionRoomChoiceBox.setVisible(true);
            sessionDateInDatePicker.setVisible(true);
            sessionDateOutDatePicker.setVisible(true);
            sessionPresidentChoiceBox.setItems(FXCollections.observableArrayList(controller.AllPc()));
            sessionRoomChoiceBox.setItems(FXCollections.observableArrayList(controller.AllRoom()));
            buttonOkId.setOnAction(e->Edit());
        }
    }
    public void Add()
    {
        try {

//            LocalDate localDateIn = sessionDateInDatePicker.getValue();
//            Date dateIn = java.sql.Date.valueOf(localDateIn);
//            LocalDate localDateOut = sessionDateOutDatePicker.getValue();
//            Date dateOn = java.sql.Date.valueOf(localDateOut);
            controller.add(null,null,null,sessionNameTextField.getText(),null);
            Stage stage = (Stage)buttonOkId.getScene().getWindow();
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
            LocalDate localDateIn = sessionDateInDatePicker.getValue();
            Date dateIn = java.sql.Date.valueOf(localDateIn);
            LocalDate localDateOut = sessionDateOutDatePicker.getValue();
            Date dateOut = java.sql.Date.valueOf(localDateOut);
            controller.edit(sesiune.getId_session(),dateIn,dateOut,sessionRoomChoiceBox.getSelectionModel().getSelectedItem().toString(),sessionNameTextField.getText(),sessionPresidentChoiceBox.getSelectionModel().getSelectedItem().toString());
            Stage stage = (Stage)buttonOkId.getScene().getWindow();
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