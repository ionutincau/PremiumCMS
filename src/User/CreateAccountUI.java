package User;

import Utils.UtilFunctions;
import database.DatabaseConnection;
import domain.User;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.Session;

import java.util.ArrayList;

/**
 * Created by Aurelian on 5/10/2017.
 */

public class CreateAccountUI {

    @FXML private TextField countryTextField;
    @FXML private PasswordField passwordTextField;
    @FXML private TextField phoneTextField;
    @FXML private TextField lastNameTextField;
    @FXML private ChoiceBox typeChoiceBox;
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
        ArrayList<String> list = new ArrayList();
        list.add("author");
        list.add("participant");
        list.add("pc");
        typeChoiceBox.setItems(FXCollections.observableArrayList(list));
        typeChoiceBox.getSelectionModel().selectFirst();
    }

    public void createButton() {
        User user = new User();
        user.setFirstName(firstNameTextField.getText());
        user.setLastName(lastNameTextField.getText());
        user.setUserName(usernameTextField.getText());
        user.setPassword(passwordTextField.getText());
        user.setType(typeChoiceBox.getSelectionModel().getSelectedItem().toString());
        user.setCountry(countryTextField.getText());
        user.setAffiliation(affiliationTextField.getText());
        user.setEmail(emailTextField.getText());
        user.setPhone(phoneTextField.getText());
        user.setWebPage(websiteTextField.getText());
        user.setStatus("pending");

        if (user.getUserName().isEmpty() || user.getPassword().isEmpty() || user.getEmail().isEmpty() || user.getFirstName().isEmpty() || user.getLastName().isEmpty()) {
            UtilFunctions.showInfo("Please fill required fields!");
        }
        else {
            Session session = DatabaseConnection.getInstance().openSession();
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            session.close();

            Stage stage = (Stage) okButton.getScene().getWindow();
            stage.close();
        }
    }
}
