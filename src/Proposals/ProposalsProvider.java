package Proposals;

import domain.Proposal;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MariusDK on 16.05.2017.
 */

public class ProposalsProvider {

    public List selectProposals() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Proposal> proposals = session.createQuery("from Proposal ").getResultList();
        session.getTransaction().commit();
        session.close();
        return proposals;
    }

    public void insert(Proposal p)
    {
        SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        session.save(p);
        session.getTransaction().commit();
        session.close();
    }
    public void update(Proposal p)
    {
        SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        session.update(p);
        session.getTransaction().commit();
        session.close();
    }
    public void delete(Proposal p) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(p);
        session.getTransaction().commit();
        session.close();
    }
    public int getIdAuthorByName(String AuthorName)
    {
        String[] listName=AuthorName.split(" ", 2);
        String lastName=listName[0];
        String firstName=listName[1];
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "SELECT id_user FROM User where firstName=:Fname and lastName=:Lname";
        Query query = session.createQuery(hql);
        query.setParameter("Fname", firstName);
        query.setParameter("Lname", lastName);
        int id_author=(int)query.getSingleResult();
        session.getTransaction().commit();
        session.close();
        return id_author;
    }
    public int getIdSesiuneByName(String sesiune)
    {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "SELECT id_session FROM Sesiune where name=:Nsesiune";
        Query query = session.createQuery(hql);
        query.setParameter("Nsesiune", sesiune);
        int id_sesiune=(int)query.getSingleResult();
        session.getTransaction().commit();
        session.close();
        return id_sesiune;
    }
    public List getAllNameeSessions()
    {
        List<String> sesiunesName=new ArrayList<>();
        SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        sesiunesName=session.createQuery("select name from Sesiune ").getResultList();
        session.getTransaction().commit();
        session.close();
        return sesiunesName;
    }
    public String getSessionName(int id_session)
    {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "SELECT name FROM Sesiune where id_session=:idSession";
        Query query = session.createQuery(hql);
        query.setParameter("idSession", id_session);
        String name=(String) query.getSingleResult();
        session.getTransaction().commit();
        session.close();
        return name;
    }




}
