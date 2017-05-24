package Presentations;

import Utils.UtilFunctions;
import domain.Presentation;
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
 * Created by ASUS on 23.May.2017.
 */

public class PresentationTabUIController implements Initializable, Observer {
    private PresentationsController controller;

    @FXML private Button presentationSeeDetailsButton;
    @FXML private Button presentationAddDocButton;
    @FXML private ListView presentationsListView;

    public PresentationTabUIController() {
            this.controller=new PresentationsController();
            this.controller.addObserver(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        presentationsListView.setFixedCellSize(48);
        presentationsListView.getItems().addAll(0, (ArrayList)controller.getAllPresentations());
        presentationAddDoc();
    }

    private void loadWindow(String name, Presentation presentation) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("add_demo.fxml"));
            AnchorPane root = loader.load();
            PresentationAddDocUI editController = loader.<PresentationAddDocUI>getController();
            editController.initData(name,presentation,controller);
            Stage stage = new Stage();
            stage.setTitle(name);
            stage.setScene(new Scene(root, 800, 400));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void presentationAddDoc() {
        presentationAddDocButton.setOnAction(event -> {
            presentationsListView.getSelectionModel().getSelectedIndex();
            Presentation presentation = (Presentation) presentationsListView.getSelectionModel().getSelectedItem();
            if (presentation != null) {
                loadWindow("AddDoc", presentation);
            }
            else UtilFunctions.showInfo("Selectati o prezentare");
        });
    }

    public void presentationDetails() {
        presentationSeeDetailsButton.setOnAction(event -> {
            presentationsListView.getSelectionModel().getSelectedIndex();
            Presentation presentation = (Presentation) presentationsListView.getSelectionModel().getSelectedItem();
            if (presentation != null) {
                loadWindow("SeeDetails", presentation);
            }
            else UtilFunctions.showInfo("Selectati o prezentare");
        });
    }


    @Override
    public void update(Observable o, Object arg) {
        presentationsListView.getItems().clear();
        presentationsListView.getItems().addAll(0,(ArrayList)controller.getAllPresentations());
    }
}
