package Events;

import domain.Event;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MariusDK on 16.05.2017.
         */
public class EventsProvider {
    public List select()
    {
        List<Event> events=new ArrayList<>();
        SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        events=session.createQuery("from Event ").getResultList();
        session.getTransaction().commit();
        session.close();
        return events;
    }
    public void insert(Event e)
    {
        SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        session.save(e);
        session.getTransaction().commit();
        session.close();
    }
    public void update(Event e)
    {
        SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        session.update(e);
        session.getTransaction().commit();
        session.close();
    }
    public void delete(Event e) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(e);
        session.getTransaction().commit();
        session.close();
    }






}
