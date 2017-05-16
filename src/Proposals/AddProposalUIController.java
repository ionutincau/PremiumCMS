package Proposals;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Created by Aurelian on 5/11/2017.
 */
public class AddProposalUIController {

    @FXML
    private ChoiceBox<String> proposalSessionChoiceBox;

    @FXML
    private Button proposalOKButton;

    @FXML
    private TextField proposalTypeTextField;

    @FXML
    private Label proposalDocumentPathLabel;

    @FXML
    private Label proposalAbstractPathLabel;

    @FXML
    private Button proposalDocumentFilePicker;

    @FXML
    private TextField proposalKeywordsTextField;

    @FXML
    private TextField proposalTopicsTextField;

    @FXML
    private TextField proposalNameTextField;

    @FXML
    private Button proposalAbstractFilePicker;


    public AddProposalUIController()
    {

    }

    public void initialize()
    {

    }
}
