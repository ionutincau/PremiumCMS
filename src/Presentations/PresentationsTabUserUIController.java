package Presentations;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.Observer;
import java.util.ResourceBundle;

/**
 * Created by Aurelian on 5/23/2017.
 */
public class PresentationsTabUserUIController implements Observer, Initializable{

    @FXML
    private Button presentationParticipateButton;

    @FXML
    private ListView<String> presentationsListView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void update(java.util.Observable o, Object arg) {

    }
}
