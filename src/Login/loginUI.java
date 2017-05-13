package Login;

import domain.User;
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
    @FXML private TabPane tabPane;

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
//        if (loginController.getUser() != null) {
//            addTabs("Admin");
//            closeTab(loginTab);
//        }
        User user=loginController.getUser();
        if (user!=null)
        {
            closeTab(loginTab);
            addTabs(user.getType());
            closeTab(loginTab);
        }
    }

    private void showInfo(String info) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(info);
        alert.show();
    }
    public void addTabs(String usertype) {
        try {
            if (usertype.equals("admin"))
            {
                Tab Sesiune = FXMLLoader.load(this.getClass().getResource("../Sessions/sessions_tab.fxml"));
                tabPane.getTabs().add(Sesiune);

            }
            if (usertype.equals("participant"))
            {

            }
            if (usertype.equals("spectator"))
            {

            }
        }
        catch (IOException ex) {
           // UtilFunctions.showInfo("Application can't manage usertype " + usertype + "\nContact system administrator");
        }


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
