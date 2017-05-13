package User;

import domain.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Created by Aurelian on 5/10/2017.
 */

public class CreateAccountUI {

    @FXML private TextField countryTextField;
    @FXML private PasswordField passwordTextField;
    @FXML private TextField phoneTextField;
    @FXML private TextField lastNameTextField;
    @FXML private ChoiceBox<?> statusChoiceBox;
    @FXML private TextField emailTextField;
    @FXML private TextField affiliationTextField;
    @FXML private TextField firstNameTextField;
    @FXML private Button okButton;
    @FXML private TextField websiteTextField;
    @FXML private TextField usernameTextField;

    public CreateAccountUI() {

    }

    @FXML
    public void initialize() {

    }

    public void createButton() {
        User user = new User();
        user.setFirstName(firstNameTextField.getText());
        user.setLastName(lastNameTextField.getText());
        user.setUserName(usernameTextField.getText());
        user.setPassword(passwordTextField.getText());
        user.setCountry(countryTextField.getText());
        user.setAffiliation(affiliationTextField.getText());
        user.setEmail(emailTextField.getText());
        user.setPhone(phoneTextField.getText());
        user.setWebPage(websiteTextField.getText());
        user.setType("pending");
    }
}
