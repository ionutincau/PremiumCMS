package Proposals;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Aurelian on 5/17/2017.
 */
public class PendingProposalsUI implements Initializable{

    private ProposalsController controller;
    @FXML
    private ListView<String> proposalsListView1;

    @FXML
    private Button proposalViewButton;

    public PendingProposalsUI()
    {
        this.controller=new ProposalsController();
        //this.controller.addObserver(this);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        proposalsListView1.setFixedCellSize(48);
        proposalsListView1.getItems().addAll(0,(ArrayList)controller.getProposal());
        proposalViewButton.setOnAction(e->WannaReview());
    }
    public void WannaReview()
    {
        
    }
}
