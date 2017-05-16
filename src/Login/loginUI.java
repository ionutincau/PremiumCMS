package Login;

import Utils.UtilFunctions;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Incau Ionut on 14-Mar-17.
 * Contact: ionut.incau@gmail.com
 */

public class loginUI {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private TabPane tabPane;
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
            UtilFunctions.showInfo(e.getMessage());
        }
        if (loginController.getUser() != null) {
            closeTab(loginTab);
            addTabs(loginController.getUser().getType());
            //todo: remove temp for test
//            try {
//                Tab pending = FXMLLoader.load(this.getClass().getResource("../User/pending_users_tab.fxml"));
//                tabPane.getTabs().add(pending);
//            }
//            catch (Exception e) {
//
//            }
        }
    }
    public void addTabs(String usertype) {
        try {
            if (usertype.equals("Admin")) {
                Tab pending = FXMLLoader.load(this.getClass().getResource("../User/pending_users_tab.fxml"));
                Tab sesiuneTab = FXMLLoader.load(this.getClass().getResource("../Sessions/sessions_tab.fxml"));
                Tab evenimentTab = FXMLLoader.load(this.getClass().getResource("../Events/events_tab.fxml"));
                Tab propuneriTab = FXMLLoader.load(this.getClass().getResource("../Proposals/proposals_tab.fxml"));
                tabPane.getTabs().add(pending);
                tabPane.getTabs().add(sesiuneTab);
                tabPane.getTabs().add(evenimentTab);
                tabPane.getTabs().add(propuneriTab);

            }
            if (usertype.equals("pc")) {
                Tab pending = FXMLLoader.load(this.getClass().getResource("../User/pending_users_tab.fxml"));
                tabPane.getTabs().add(pending);
            }
            if (usertype.equals("particitapnti"))
            {

            }

        }
        catch (IOException ex) {
            UtilFunctions.showInfo("Application can't manage usertype " + usertype + "\nContact system administrator");
        }
    }
    public void createAccount() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../User/create_account.fxml"));
            AnchorPane root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Create new account");
            stage.setScene(new Scene(root, 750, 500));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    // todo:
    private void closeTab(Tab tab) {
        EventHandler<Event> handler = tab.getOnClosed();
        if (null != handler) {
            handler.handle(null);
        }
        else {
            tab.getTabPane().getTabs().remove(tab);
        }
    }
}
