package database;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by ionut on 09-Apr-17.
 */

public class DatabaseConnection {

    private static DatabaseConnection instance;
    private static SessionFactory sessionFactory;

    private DatabaseConnection() {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        catch(HibernateException exception){
            System.out.println("Connection with database failed");
            System.out.println("Check internet connection!");
        }
    }

    public static SessionFactory getInstance(){
        if (instance == null){
            instance = new DatabaseConnection();
        }
        return sessionFactory;
    }
}
