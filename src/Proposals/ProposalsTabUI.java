package Proposals;

import domain.Proposal;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

/**
 * Created by Aurelian on 5/11/2017.
 */

public class ProposalsTabUI implements Initializable,Observer{

    private ProposalsController controller;

    @FXML private Button proposalDeleteButton;
    @FXML private ListView<Proposal> proposalsListView;
    @FXML private Button proposalAddButton;
    @FXML private Button proposalEditButton;

    public ProposalsTabUI() {
        this.controller = new ProposalsController();
        this.controller.addObserver(this);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        proposalsListView.setFixedCellSize(48);
        proposalsListView.getItems().addAll(0, (ArrayList)controller.getProposal());
        ProposalADD();
        ProposalEdit();
        ProposalDelete();
    }

    private void loadWindow(String name, Proposal proposal) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("add_proposal.fxml"));
            AnchorPane root = loader.load();
            AddProposalUI editController = loader.<AddProposalUI>getController();
            editController.initData(name,proposal,controller);
            Stage stage = new Stage();
            stage.setTitle(name);
            stage.setScene(new Scene(root, 300, 250));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ProposalADD()
    {
        proposalAddButton.setOnAction(event -> loadWindow("Adauga",null));
    }

    public void ProposalEdit() {
        proposalEditButton.setOnAction(event -> {
            proposalsListView.getSelectionModel().getSelectedIndex();
            Proposal proposal = (Proposal) proposalsListView.getSelectionModel().getSelectedItem();
            if (proposal != null) {
                loadWindow("Editare", proposal);
            }
            //else UtilFunctions.showInfo("Selectati o propunere"); // todo: check this
        });
    }

    public void ProposalDelete() {
        proposalDeleteButton.setOnAction(e->{
            proposalsListView.getSelectionModel().getSelectedIndex();
            Proposal proposal = (Proposal) proposalsListView.getSelectionModel().getSelectedItem();
            if (proposal!=null) {
                controller.delete(proposal);
            }
            //else UtilFunctions.showInfo("Selectati o sesiune"); // todo: check this
        });
    }

    @Override
    public void update(Observable o, Object arg) {
        proposalsListView.getItems().clear();
        proposalsListView.getItems().addAll(0,(ArrayList)controller.getProposal());
    }
}
