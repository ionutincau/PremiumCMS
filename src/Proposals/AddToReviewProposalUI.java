package Proposals;

import Login.LoginController;
import Utils.UtilFunctions;
import domain.Proposal;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Aurelian on 5/17/2017.
 */

public class AddToReviewProposalUI implements Initializable{

    private ProposalsController controller;
    private Proposal proposal;

    @FXML private Hyperlink proposalDocumentSeeFile;
    @FXML private Label proposalTopicsLabel;
    @FXML private Hyperlink proposalAbstractSeeFile;
    @FXML private Label proposalKeywordsLabel;
    @FXML private Button proposalPassbutton;
    @FXML private Label proposalDocumentFilename;
    @FXML private Button proposalReviewButton;
    @FXML private Label proposalNameLabel;
    @FXML private Label proposalAbstractFilename;
    @FXML private Label proposalSessionLabel;

    public AddToReviewProposalUI() {

    }

    public void initialize(URL fxmlFileLocation, ResourceBundle resourceBundle) {

    }

    public void initData(String name, Proposal proposal, ProposalsController controller) {
        this.controller = controller;
        this.proposal = proposal;
        proposalTopicsLabel.setText(proposal.getTopics());
        proposalKeywordsLabel.setText(proposal.getKeywords());
        proposalDocumentFilename.setText(proposal.getDocument());
        proposalAbstractFilename.setText(proposal.getAbs());
        proposalNameLabel.setText(proposal.getName());
        proposalSessionLabel.setText(controller.getSessionName(proposal.getId_session()));
        proposalReviewButton.setOnAction(e->Review());
        proposalPassbutton.setOnAction(e->Pass());
    }

    public void Review() {
        controller.UpdatePcProposal(LoginController.getInstance().getUser(),proposal,1, "",null);
        Stage stage = (Stage) proposalReviewButton.getScene().getWindow();
        stage.close();
    }

    public void Pass() {
        controller.UpdatePcProposal(LoginController.getInstance().getUser(),proposal,0, "",null);
        Stage stage = (Stage) proposalPassbutton.getScene().getWindow();
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
