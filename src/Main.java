import database.DatabaseConnection;

import domain.Payment;
import domain.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;

import java.sql.Date;

/**
 * Created by Incau Ionut on 22-Mar-17.
 * Contact: ionut.incau@gmail.com
 */

public class Main extends Application {

    public static void main(String[] args) {

        Payment payment=new Payment();
        String str="2015-07-15";
        Date date=Date.valueOf(str);//converting string into sql date
        //System.out.println(date);
        payment.setDate(date);

        Payment payment2=new Payment();
        String str2="2015-07-14";
        Date date2=Date.valueOf(str2);//converting string into sql date
        //System.out.println(date2);
        payment2.setDate(date2);

        User user = new User();
        user.setUserId(1);
        user.getPayment().add(payment);
        user.getPayment().add(payment2);
        user.setUserName("johnny");
        user.setPassword("new");


        Session session = DatabaseConnection.getInstance().openSession();
        session.beginTransaction();
        session.save(payment);
        session.save(payment2);
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
