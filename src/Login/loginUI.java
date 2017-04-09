package Login;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import java.io.IOException;

/**
 * Created by Incau Ionut on 14-Mar-17.
 * Contact: ionut.incau@gmail.com
 */

public class loginUI {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Tab loginTab;

    private LoginController loginController;

    public loginUI() {
        loginController = new LoginController();
    }

    public void Login() {
        try {
            loginController.login(usernameField.getText(), passwordField.getText());
        }
        catch (Exception e) {
            showInfo(e.getMessage());
        }
        if (loginController.getUser() != null) {
            closeTab(loginTab);
        }
    }

    private void showInfo(String info) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(info);
        alert.show();
    }

    // todo:
    private void closeTab(Tab tab) {
        EventHandler<Event> handler = tab.getOnClosed();
        if (null != handler) {
            handler.handle(null);
        } else {
            tab.getTabPane().getTabs().remove(tab);
        }
    }
}
