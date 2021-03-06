package Sessions;

import Utils.UtilFunctions;
import domain.Sesiune;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * Created by Aurelian on 5/11/2017.
 */

public class AddSessionUIController implements Initializable{

    @FXML private ChoiceBox<String> sessionPresidentChoiceBox;
    @FXML private Button buttonOkId;
    @FXML private DatePicker sessionDateOutDatePicker;
    @FXML private ChoiceBox<String> sessionRoomChoiceBox;
    @FXML private DatePicker sessionDateInDatePicker;
    @FXML private TextField sessionNameTextField;
    @FXML private Label sessionRoomLabel;
    @FXML private Label sessionDateInLabel;
    @FXML private Label sessionDateOutLabel;
    @FXML private Label sessionPresidentLabel;

    private SesiuneController controller;
    private Sesiune sesiune;

    public AddSessionUIController() {

    }

    public void initialize(URL fxmlFileLocation, ResourceBundle resourceBundle) {

    }

    public void initData(String name, Sesiune sesiune, SesiuneController controller) {
        this.controller = controller;
        this.sesiune = sesiune;

        sessionPresidentChoiceBox.setVisible(false);
        sessionRoomChoiceBox.setVisible(false);
        sessionDateInDatePicker.setVisible(false);
        sessionDateOutDatePicker.setVisible(false);
        sessionRoomLabel.setVisible(false);
        sessionDateInLabel.setVisible(false);
        sessionDateOutLabel.setVisible(false);
        sessionPresidentLabel.setVisible(false);

        buttonOkId.setText(name);
        if (name == "Adauga") {
            buttonOkId.setOnAction(e->Add());
        }
        else {
            sessionPresidentChoiceBox.setVisible(true);
            sessionRoomChoiceBox.setVisible(true);
            sessionDateInDatePicker.setVisible(true);
            sessionDateOutDatePicker.setVisible(true);
            sessionRoomLabel.setVisible(true);
            sessionDateInLabel.setVisible(true);
            sessionDateOutLabel.setVisible(true);
            sessionPresidentLabel.setVisible(true);

            sessionPresidentChoiceBox.setItems(FXCollections.observableArrayList(controller.AllPc()));

            if (sesiune.getPresident()!=null) {
                sessionPresidentChoiceBox.setValue(sesiune.getPresident());
            }
            if (sesiune.getId_room()!=0) {
                sessionRoomChoiceBox.setValue(controller.getRoom((sesiune.getId_room())));
            }
            sessionRoomChoiceBox.setItems(FXCollections.observableArrayList(controller.AllRoom()));
            if (sesiune.getDate_in()!=null) {
                sessionDateInDatePicker.setValue(sesiune.getDate_in().toLocalDate());
            }
            if (sesiune.getDate_out()!=null) {
                sessionDateOutDatePicker.setValue(sesiune.getDate_out().toLocalDate());
            }
            sessionNameTextField.setText(sesiune.getName());
            buttonOkId.setOnAction(e->Edit());
        }
    }

    public void Add() {
        try {
            controller.add(null,null,null, sessionNameTextField.getText(),null);
            Stage stage = (Stage)buttonOkId.getScene().getWindow();
            stage.close();
        }
        catch (NumberFormatException e) {
            UtilFunctions.showInfo("Format invalid!\n");
        }
        catch (Exception e) {
            UtilFunctions.showInfo(e.getMessage());
        }
    }

    public void Edit() {
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
            UtilFunctions.showInfo("Format invalid!\n");
        }
        catch (Exception e) {
            UtilFunctions.showInfo(e.getMessage());
        }
    }
}