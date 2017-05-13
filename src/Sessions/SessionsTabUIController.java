package Sessions;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import org.omg.CORBA.NO_RESOURCES;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Aurelian on 5/11/2017.
 */
public class SessionsTabUIController {

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

    }
    public void initialize(URL fxmlFileLocation, ResourceBundle resourceBucccndle)
    {
        sessionsListView.setFixedCellSize(48);
        sessionsListView.getItems().addAll(0,controller.getSesiune());
        SesiuneADD();

    }
}
