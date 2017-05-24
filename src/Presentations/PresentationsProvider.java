package Presentations;

import domain.Presentation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.*;

/**
 * Created by ASUS on 23.May.2017.
 */
public class PresentationsProvider {

    public List select()
    {
        SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        List<Presentation> presentations=session.createQuery("from Presentation").getResultList();
        session.getTransaction().commit();
        session.close();
        return presentations;
    }

    public void insert(Presentation p)
    {
        SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        session.save(p);
        session.getTransaction().commit();
        session.close();
    }
    public void update(Presentation p)
    {
        SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        session.update(p);
        session.getTransaction().commit();
        session.close();
    }
    public void delete(Presentation p) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(p);
        session.getTransaction().commit();
        session.close();
    }

}