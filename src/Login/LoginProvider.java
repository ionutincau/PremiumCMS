package Login;

import database.DatabaseConnection;
import domain.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 * Created by Incau Ionut on 14-Mar-17.
 * Contact: ionut.incau@gmail.com
 */

public class LoginProvider {

    public LoginProvider() {

    }

    public User getUser(String username) {
        Session session = DatabaseConnection.getInstance().openSession();
        session.beginTransaction();

        Query query = session.createQuery("from User where userName=:userName");
        query.setParameter("userName", username);
        System.out.println("Da");
        User user = (User) query.uniqueResult();

        session.getTransaction().commit();
        session.close();
        return user;
    }
}
