package Sessions;

import domain.Sesiune;
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

public class SessionsTabUIController implements Initializable,Observer{

    private SesiuneController controller;
    @FXML
    private Button addSessionButton;

    @FXML
    private ListView<?> sessionsListView;

    @FXML
    private Button deleteSessionButton;

    @FXML
    private Button editSessionButton;


    public SessionsTabUIController()
    {
        this.controller=new SesiuneController();
        this.controller.addObserver(this);
        //LoginController.getInstance().addObserver(this);
    }
    public void initialize(URL fxmlFileLocation, ResourceBundle resourceBucccndle)
    {

        sessionsListView.setFixedCellSize(48);
        sessionsListView.getItems().addAll(0,(ArrayList)controller.getSesiune());
        SesiuneADD();
        SesiuneEdit();
        SesiuneDelete();

    }
    private void loadWindow(String name, Sesiune sesiune)
    {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("add_session.fxml"));
            AnchorPane root = loader.load();
            AddSessionUIController editController = loader.<AddSessionUIController>getController();
            editController.initData(name,sesiune,controller);
            Stage stage = new Stage();
            stage.setTitle(name);
            stage.setScene(new Scene(root, 300, 250));
            stage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public void SesiuneADD()
    {
        addSessionButton.setOnAction(event -> loadWindow("Adauga",null));
    }

    public void SesiuneEdit()
    {
        editSessionButton.setOnAction(event -> {
            sessionsListView.getSelectionModel().getSelectedIndex();
            Sesiune sesiune = (Sesiune) sessionsListView.getSelectionModel().getSelectedItem();
            if (sesiune != null) {
                loadWindow("Editare", sesiune);
            }
            //else UtilFunctions.showInfo("Selectati o sesiune");
        });
    }
    public void SesiuneDelete()
    {
        deleteSessionButton.setOnAction(e->{
            sessionsListView.getSelectionModel().getSelectedIndex();
            Sesiune sesiune=(Sesiune) sessionsListView.getSelectionModel().getSelectedItem();
            if (sesiune!=null)
            {
                controller.delete(sesiune);
            }
            //else UtilFunctions.showInfo("Selectati o sesiune");
        });
    }

    @Override
    public void update(Observable o, Object arg) {
        sessionsListView.getItems().clear();
        sessionsListView.getItems().addAll(0,(ArrayList)controller.getSesiune());
    }

}
