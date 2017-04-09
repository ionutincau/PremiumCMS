import database.DatabaseConnection;
import domain.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;

/**
 * Created by Incau Ionut on 22-Mar-17.
 * Contact: ionut.incau@gmail.com
 */

public class Main extends Application {

    public static void main(String[] args) {
        User user = new User();
        user.setUserId(1);
        user.setUserName("johnny");
        user.setPassword("new");

        Session session = DatabaseConnection.getInstance().openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();

        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Login/login.fxml"));
        primaryStage.setTitle("Premium");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
