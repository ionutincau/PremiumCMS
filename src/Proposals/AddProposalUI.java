package Proposals;

import Login.LoginController;
import Utils.UtilFunctions;
import domain.Proposal;
import domain.User;
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
        if (name == "Add") {
            proposalOKButton.setOnAction(e->Add());
        }
        else {
            proposalSessionChoiceBox.setValue(controller.getSessionName(proposal.getId_session()));
            proposalOtherAuthorsNameTextField.setText(proposal.getOther_authors());
            proposalTopicsTextField.setText(proposal.getTopics());
            proposalNameTextField.setText(proposal.getName());
            proposalKeywordsTextField.setText(proposal.getKeywords());
            proposalOKButton.setOnAction(e->Edit());
        }
    }

    public void Add() {
        try {
            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            if (proposalSessionChoiceBox.getSelectionModel().getSelectedItem() != null) {
                User user = LoginController.getInstance().getUser();
                controller.add(user, proposalOtherAuthorsNameTextField.getText(), proposalNameTextField.getText(), proposalKeywordsTextField.getText(), proposalTopicsTextField.getText(), "", date, null, "pending", null, null, proposalSessionChoiceBox.getSelectionModel().getSelectedItem());
                Stage stage = (Stage) proposalOKButton.getScene().getWindow();
                stage.close();
            }
            else UtilFunctions.showInfo("Select a sesion for your proposal");
        }
        catch (NumberFormatException e) {
            UtilFunctions.showInfo("Format invalid!\n");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void Edit() {
        try {
            if (proposalSessionChoiceBox.getSelectionModel().getSelectedItem() != null) {
                User user = LoginController.getInstance().getUser();
                controller.edit(proposal, user, proposalOtherAuthorsNameTextField.getText(), proposalNameTextField.getText(), proposalKeywordsTextField.getText(), proposalTopicsTextField.getText(), "", proposal.getSend_date(), null, null, null, null, proposalSessionChoiceBox.getSelectionModel().getSelectedItem());
                Stage stage = (Stage) proposalOKButton.getScene().getWindow();
                stage.close();
            }
            else UtilFunctions.showInfo("Select a sesion for your proposal");
        }
        catch (NumberFormatException e) {
            UtilFunctions.showInfo("Format invalid!\n");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
