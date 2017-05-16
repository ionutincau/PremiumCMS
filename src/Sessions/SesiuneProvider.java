package Sessions;

import domain.Sesiune;
import domain.User;
import org.hibernate.*;
import org.hibernate.Cache;
import org.hibernate.boot.spi.SessionFactoryOptions;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.spi.FilterDefinition;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.metadata.CollectionMetadata;
import org.hibernate.stat.Statistics;

import javax.naming.NamingException;
import javax.naming.Reference;
import javax.persistence.*;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by MariusDK on 13.05.2017.
 */
public class SesiuneProvider
{
    public void insert(Sesiune s)
    {
        SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        session.save(s);
        session.getTransaction().commit();
        session.close();
    }
    public List select()
    {
        List<Sesiune> sesiunes=new ArrayList<>();
        SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        sesiunes=session.createQuery("from Sesiune ").getResultList();
        session.getTransaction().commit();
        session.close();
        return sesiunes;
    }
    public void update(Sesiune sesiune)
    {
        SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        session.update(sesiune);
        session.getTransaction().commit();
        session.close();
    }
    public void delete(Sesiune sesiune) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(sesiune);
        session.getTransaction().commit();
        session.close();
    }
    public int get_next_ID()
    {
        int id;
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Sesiune result = (Sesiune) session.createQuery("from Sesiune ORDER BY id_session DESC")
                .setMaxResults(1)
                .uniqueResult();
        id=result.getId_session();
        session.getTransaction().commit();
        session.close();
        return id;
    }
    public List<String> getC()
    {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        String hql = "SELECT concat(firstName, ' ' ,lastName) AS fullname FROM User where type='pc'";
        Query query = session.createQuery(hql);
        List results = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return results;
    }
    public List<String> getRoom()
    {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "SELECT name FROM Room ";
        Query query = session.createQuery(hql);
        List<String> results = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return results;
    }
    public int getIdRoomFromName(String name)
    {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        String hql = "SELECT id_room FROM Room where name=:name";
        Query query = session.createQuery(hql);
        query.setParameter("name", name);
        int id_room=(int)query.getSingleResult();
        session.getTransaction().commit();
        session.close();
        return id_room;
    }
    public String getRoomNameById(int id_room)
    {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        String hql = "SELECT name FROM Room where id_room=:id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id_room);
        String RoomName=(String) query.getSingleResult();
        session.getTransaction().commit();
        session.close();
        return RoomName;
    }

}
