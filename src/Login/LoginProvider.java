package Login;

import database.DatabaseConnection;
import domain.User;
import org.hibernate.Session;

/**
 * Created by ionut on 09-Apr-17.
 */

public class LoginProvider {

    public LoginProvider() {

    }

    public User getUser(String username) {
        Session session = DatabaseConnection.getInstance().openSession();
        session.beginTransaction();

        // todo: get by username
        User user = session.get(User.class, 1);

        session.getTransaction().commit();
        session.close();
        return user;
    }
}
