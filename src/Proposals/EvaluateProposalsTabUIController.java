package Proposals;

import Utils.UtilFunctions;
import domain.ProposalEvalPC;
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
import java.util.ResourceBundle;

/**
 * Created by Aurelian on 5/19/2017.
 */
public class EvaluateProposalsTabUIController implements Initializable{
    private ProposalsController controller;
    @FXML
    private ListView<ProposalEvalPC> evaluateProposalsListView;

    @FXML
    private Button proposalEvaluateButton;

    public EvaluateProposalsTabUIController()
    {
        this.controller=new ProposalsController();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

        evaluateProposalsListView.setFixedCellSize(48);
        evaluateProposalsListView.getItems().addAll(controller.getPcProposalEvalList());
        EvalTab();
    }
    public void loadWindow(String name, ProposalEvalPC proposalEvalPC) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("evaluate_proposal.fxml"));
            AnchorPane root = loader.load();
            EvaluateProposalUIController editController = loader.<EvaluateProposalUIController>getController();
            editController.initData(name,proposalEvalPC,controller);
            Stage stage = new Stage();
            stage.setTitle(name);
            stage.setScene(new Scene(root, 800, 600));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void EvalTab()
    {
        proposalEvaluateButton.setOnAction(e->{
            evaluateProposalsListView.getSelectionModel().getSelectedItem();
            ProposalEvalPC pcEval=evaluateProposalsListView.getSelectionModel().getSelectedItem();
            if (pcEval != null) {
                loadWindow("Evaluare", pcEval);
            }
            else UtilFunctions.showInfo("Selectati un eveniment");
        });
    }


}
