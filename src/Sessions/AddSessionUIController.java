package Sessions;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * Created by Aurelian on 5/11/2017.
 */
public class AddSessionUIController {

    @FXML
    private ChoiceBox<?> sessionPresidentChoiceBox;

    @FXML
    private DatePicker sessionDateOutDatePicker;

    @FXML
    private ChoiceBox<?> sessionRoomChoiceBox;

    @FXML
    private DatePicker sessionDateInDatePicker;

    @FXML
    private TextField sessionNameTextField;

    public AddSessionUIController()
    {

    }

    private void initialize()
    {

    }

}