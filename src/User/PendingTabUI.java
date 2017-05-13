package User;

import database.DatabaseConnection;
import domain.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by Aurelian on 5/10/2017.
 */

public class PendingTabUI {

    @FXML private Button deleteUserButton;
    @FXML private ListView pendingUsersListView;
    @FXML private Button approveUserButton;

    public PendingTabUI() {

    }

    public void initialize() {
        Session session = DatabaseConnection.getInstance().openSession();
        session.beginTransaction();

        // todo: get a list of pending users
        Query query = session.createQuery("from User where status=:status");
        query.setParameter("status", "pending");
        List list = query.list();

        session.getTransaction().commit();
        session.close();

        for (Object u : list) {
            System.out.println("");
            System.out.println("");
            System.out.println(((User)u).getUserName() + ((User)u).getStatus());
        }

        pendingUsersListView.setFixedCellSize(48);
        pendingUsersListView.getItems().addAll(list);
    }
}
