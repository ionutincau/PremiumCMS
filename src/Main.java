import domain.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by Incau Ionut on 22-Mar-17.
 * Contact: ionut.incau@gmail.com
 */

public class Main {

    public static void main(String[] args) {
        User user = new User();
        user.setUserId(1);
        user.setUserName("Johnny");

        try {
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }
        catch(HibernateException exception){
            System.out.println("Problem creating session factory");
            exception.printStackTrace();
        }
    }
}
