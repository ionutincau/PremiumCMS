package User;

import database.DatabaseConnection;
import domain.User;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by Aurelian on 5/10/2017.
 */

public class PendingTabUI {

    @FXML private ListView pendingUsersListView;

    public PendingTabUI() {

    }

    public void initialize() {
        Session session = DatabaseConnection.getInstance().openSession();
        session.beginTransaction();

        Query query = session.createQuery("FROM User U WHERE U.status = :s");
        query.setParameter("s","pending");
        List list = query.list();

        session.getTransaction().commit();
        session.close();

        pendingUsersListView.setFixedCellSize(48);
        pendingUsersListView.getItems().addAll(list);
    }

    public void approve() {
        Session session = DatabaseConnection.getInstance().openSession();
        session.beginTransaction();

        User user = (User) pendingUsersListView.getSelectionModel().getSelectedItem();
        pendingUsersListView.getItems().remove(user);
        user.setStatus("approved");
        session.update(user);

        session.getTransaction().commit();
        session.close();
    }

    public void delete () {
        Session session = DatabaseConnection.getInstance().openSession();
        session.beginTransaction();

        User user = (User) pendingUsersListView.getSelectionModel().getSelectedItem();
        pendingUsersListView.getItems().remove(user);
        session.remove(user);

        session.getTransaction().commit();
        session.close();
    }
}
