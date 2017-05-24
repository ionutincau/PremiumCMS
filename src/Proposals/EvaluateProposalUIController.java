package Proposals;

import Login.LoginController;
import Utils.UtilFunctions;
import domain.PCProposal;
import domain.Proposal;
import domain.ProposalEvalPC;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Aurelian on 5/19/2017.
 */
public class EvaluateProposalUIController {

    private ProposalsController controller;
    private ProposalEvalPC proposalEvalPC;

    @FXML
    private Button saveButton;

    @FXML
    private Label proposalDocumentFileLabel;

    @FXML
    private Label proposalTypeLabel;

    @FXML
    private Label proposalAbstractFileLabel;

    @FXML
    private Label proposalNameLabel;

    @FXML
    private Hyperlink proposalAbstractSeeFileLink;

    @FXML
    private Label proposalTopicsLabel;

    @FXML
    private Label proposalKeywordsLabel;

    @FXML
    private Label proposalSessionLabel;

    @FXML
    private Hyperlink proposalDocumentSeeFileLink;

    @FXML
    private ChoiceBox<String> NotaProposalChoiceBox;
    @FXML
    private TextField reviewTextField;

    public EvaluateProposalUIController()
    {

    }

    public void initialize()
    {

    }
    public void initData(String name, ProposalEvalPC proposalEvalPC, ProposalsController controller) {
        this.controller=controller;
        this.proposalEvalPC=proposalEvalPC;
        NotaProposalChoiceBox.setItems(FXCollections.observableArrayList(controller.getCalificativList()));
        proposalNameLabel.setText(proposalEvalPC.getName());
        proposalTypeLabel.setText(proposalEvalPC.getType());
        proposalTopicsLabel.setText(proposalEvalPC.getTopics());
        proposalKeywordsLabel.setText(proposalEvalPC.getKeywords());
        proposalSessionLabel.setText(controller.getSessionName(proposalEvalPC.getId_session()));
        proposalAbstractSeeFileLink.setText(proposalEvalPC.getAbs());
        proposalDocumentSeeFileLink.setText(proposalEvalPC.getDocument());
        proposalAbstractSeeFileLink.setOnAction(e-> {
            if (Desktop.isDesktopSupported()) {
                try {
                    File myFile = new File(proposalEvalPC.getAbs());
                    Desktop.getDesktop().open(myFile);
                }
                catch (IOException ex) {
                    UtilFunctions.showInfo("No application registered for PDFs");
                }
            }
        });
        proposalDocumentSeeFileLink.setOnAction(e->{
            if (Desktop.isDesktopSupported()) {
                try {
                    File myFile = new File(proposalEvalPC.getDocument());
                    Desktop.getDesktop().open(myFile);
                }
                catch (IOException ex) {
                    UtilFunctions.showInfo("No application registered for PDFs");
                }
            }
        });
        if (proposalEvalPC.getNota()!=null)
        {
            NotaProposalChoiceBox.setValue(proposalEvalPC.getNota());
        }
        if (proposalEvalPC.getReview()!=null)
        {
            reviewTextField.setText(proposalEvalPC.getReview());
        }
        saveButton.setText(name);
        if (name == "Evaluare") {
            saveButton.setOnAction(e->Update());
        }

    }
    public void Update()
    {
        //Proposal proposal=new Proposal(proposalEvalPC.getId_author(),proposalEvalPC.getOther_authors(),proposalEvalPC.getName(),proposalEvalPC.getKeywords(),proposalEvalPC.getTopics(),proposalEvalPC.getType(),proposalEvalPC.getSend_date(),proposalEvalPC.getAccept_date(),proposalEvalPC.getStatus(),proposalEvalPC.getAbs(),proposalEvalPC.getDocument(),proposalEvalPC.getId_session());
        Proposal proposal=controller.get_spec_proposal(proposalEvalPC.getId_proposal());
        controller.updatePCProEval(proposalEvalPC.getId_PCproposal(),LoginController.getInstance().getUser(),proposal,0,NotaProposalChoiceBox.getSelectionModel().getSelectedItem(),reviewTextField.getText());
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }


    public void seeAbstract() {
        String f = "C:\\Users\\ionut\\IdeaProjects\\PremiumCMS\\src\\documents\\abstract.pdf";
        UtilFunctions.seeFile(f);
    }

    public void seeFile() {
        //String f = "../documents/" + proposalDocumentFilename.getText();
        String f = "C:\\Users\\ionut\\IdeaProjects\\PremiumCMS\\src\\documents\\paper.pdf";
        UtilFunctions.seeFile(f);
    }
}
