package Proposals;

import Login.LoginController;
import domain.Proposal;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Callback;

public class PendingProposalsUI implements Initializable, Observer {
    private ProposalsController controller = new ProposalsController();

    @FXML private ListView<Proposal> proposalsListView1;
    @FXML private Button proposalViewButton;

    public PendingProposalsUI() {
        this.controller.addObserver(this);
    }

    public void initialize(URL location, ResourceBundle resources) {
        this.proposalsListView1.setFixedCellSize(48.0D);
        this.proposalsListView1.getItems().addAll(0, (ArrayList)this.controller.getProposal());
        this.SelectShowDet();
        this.proposalsListView1.setCellFactory(new Callback<ListView<Proposal>, ListCell<Proposal>>() {
            public ListCell<Proposal> call(ListView<Proposal> proposalListView) {
                return new PendingProposalsUI.ColorRectCell();
            }
        });
    }

    private void loadWindow(String name, Proposal proposal) {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("add_to_review_proposal.fxml"));
            AnchorPane root = (AnchorPane)loader.load();
            AddToReviewProposalUI editController = (AddToReviewProposalUI)loader.getController();
            editController.initData(name, proposal, this.controller);
            Stage stage = new Stage();
            stage.setTitle(name);
            stage.setScene(new Scene(root, 300.0D, 250.0D));
            stage.show();
        }
        catch (IOException var7) {
            var7.printStackTrace();
        }
    }

    public void SelectShowDet() {
        this.proposalViewButton.setOnAction((event) -> {
            this.proposalsListView1.getSelectionModel().getSelectedIndex();
            Proposal proposal = (Proposal)this.proposalsListView1.getSelectionModel().getSelectedItem();
            if(proposal != null) {
                this.loadWindow("Detalii", proposal);
            }
        });
    }

    public void update(Observable o, Object arg) {
        this.proposalsListView1.getItems().clear();
        this.proposalsListView1.getItems().addAll(0, (ArrayList)this.controller.getProposal());
        this.proposalsListView1.setCellFactory(new Callback<ListView<Proposal>, ListCell<Proposal>>() {
            public ListCell<Proposal> call(ListView<Proposal> proposalListView) {
                return new PendingProposalsUI.ColorRectCell();
            }
        });
    }

    static class ColorRectCell extends ListCell<Proposal> {
        private ProposalsController controller;
        private final Rectangle rect;
        public PseudoClass inactive = PseudoClass.getPseudoClass("inactive");

        ColorRectCell() {
            this.setContentDisplay(ContentDisplay.CENTER);
            this.rect = new Rectangle(800, 45);
        }

        public void updateItem(Proposal item, boolean empty) {
            this.controller = new ProposalsController();
            super.updateItem(item, empty);
            if (item != null) {
                if (this.controller.checkIfPass(LoginController.getInstance().getUser(), item)) {
                    this.rect.setFill(Color.web("#D50000"));
                    this.setGraphic(this.rect);
                }
                if (this.controller.checkIfReview(LoginController.getInstance().getUser(), item)) {
                    this.rect.setFill(Color.web("#76FF03"));
                    this.setGraphic(this.rect);
                }
            }
            else {
                this.setGraphic((Node)null);
            }
            setText(empty ? null : item.toString());
            pseudoClassStateChanged(inactive, item != null && item.toString().endsWith(" - not active"));
        }
    }
}