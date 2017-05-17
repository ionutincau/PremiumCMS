package Proposals;

import Login.LoginController;
import domain.Proposal;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by Aurelian on 5/11/2017.
 */

public class AddProposalUI implements Initializable{
    private ProposalsController controller;
    private Proposal proposal;

    @FXML private ChoiceBox<String> proposalSessionChoiceBox;
    @FXML private TextField proposalOtherAuthorsNameTextField;
    @FXML private Button proposalOKButton;
    @FXML private TextField proposalTypeTextField;
    @FXML private Label proposalDocumentPathLabel;
    @FXML private Label proposalAbstractPathLabel;
    @FXML private Button proposalDocumentFilePicker;
    @FXML private TextField proposalKeywordsTextField;
    @FXML private TextField proposalTopicsTextField;
    @FXML private TextField proposalNameTextField;
    @FXML private Button proposalAbstractFilePicker;

    public AddProposalUI() {

    }

    public void initialize(URL fxmlFileLocation, ResourceBundle resourceBundle) {}

    public void initData(String name, Proposal proposal, ProposalsController controller) {
        this.controller = controller;
        this.proposal = proposal;

        proposalSessionChoiceBox.setItems(FXCollections.observableArrayList(controller.SessionName()));
        proposalOKButton.setText(name);
        if (name == "Adauga") {
            proposalOKButton.setOnAction(e->Add());
        }
        else {
            proposalSessionChoiceBox.setValue(controller.getSessionName(proposal.getId_session()));
            proposalOtherAuthorsNameTextField.setText(proposal.getOther_authors());
            proposalTypeTextField.setText(proposal.getType());
            proposalTopicsTextField.setText(proposal.getTopics());
            proposalNameTextField.setText(proposal.getName());
            proposalKeywordsTextField.setText(proposal.getKeywords());
            proposalOKButton.setOnAction(e->Edit());
        }
    }

    public void Add() {
        try {

//            String Name = LoginController.getInstance().getUser().getLastName() + " " + LoginController.getInstance().getUser().getFirstName();
//            System.out.println(Name); //todo: check this

            java.sql.Date date= new java.sql.Date(Calendar.getInstance().getTime().getTime());
            if (!popUpErrorSession(proposalSessionChoiceBox.getSelectionModel().getSelectedItem()))
            {
                controller.add("Maierescu Marius", proposalOtherAuthorsNameTextField.getText(), proposalNameTextField.getText(), proposalKeywordsTextField.getText(), proposalTopicsTextField.getText(), proposalTypeTextField.getText(), date, null, null, null, null, proposalSessionChoiceBox.getSelectionModel().getSelectedItem());
                Stage stage = (Stage) proposalOKButton.getScene().getWindow();
                stage.close();
            }
        }
        catch (NumberFormatException e) {
            //UtilFunctions.showInfo("Format invalid!\n"); //todo: check this
        }
        catch (Exception e) {
            //UtilFunctions.showInfo(e.getMessage());
        }
    }
    public void Edit() {
        try {
            if (!popUpErrorSession(proposalSessionChoiceBox.getSelectionModel().getSelectedItem()))
            {
                //String Name = LoginController.getInstance().getUser().getLastName() + " " + LoginController.getInstance().getUser().getFirstName();
                controller.edit(proposal.getId_proposal(), "Maierescu Marius", proposalOtherAuthorsNameTextField.getText(), proposalNameTextField.getText(), proposalKeywordsTextField.getText(), proposalTopicsTextField.getText(), proposalTypeTextField.getText(), proposal.getSend_date(), null, null, null, null, proposalSessionChoiceBox.getSelectionModel().getSelectedItem());

                Stage stage = (Stage) proposalOKButton.getScene().getWindow();
                stage.close();
            }
        }
        catch (NumberFormatException e) {
            //UtilFunctions.showInfo("Format invalid!\n"); //todo: check this
        }
        catch (Exception e) {
            //UtilFunctions.showInfo(e.getMessage());
        }
    }
    public boolean popUpErrorSession(String check)
    {
        if (check.equals(null))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Sesiune");
            alert.setHeaderText("Information Alert");
            String s ="Selectati o sesiune in care sa va prezentati propunerea";
            alert.setContentText(s);
            alert.showAndWait();

            return true;
        }
        return false;
    }
}
