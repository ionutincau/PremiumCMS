package Proposals;

import domain.PCProposal;
import domain.Proposal;
import domain.ProposalEvalPC;
import domain.User;
import org.hibernate.Criteria;
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

    public List getUserProposals(int id_user) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Proposal where id_author=:idAuthor");
        query.setParameter("idAuthor", id_user);
        List<Proposal> proposals = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return proposals;
    }

    public void insert(Proposal p) {
        SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        session.save(p);
        session.getTransaction().commit();
        session.close();
    }

    public void update(Proposal p) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
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

    public int getIdAuthorByName(String AuthorName) {
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

    public int getIdSesiuneByName(String sesiune) {
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

    public List getAllNameeSessions() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<String> sesiunesName = session.createQuery("select name from Sesiune ").getResultList();
        session.getTransaction().commit();
        session.close();
        return sesiunesName;
    }

    public String getSessionName(int id_session) {
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

    public List getPC() {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        String hql = "FROM User where type='pc'";
        Query query = session.createQuery(hql);
        List results = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return results;
    }

    public String getAuthorName(int id) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "SELECT concat(lastName, ' ' ,firstName) AS fullname FROM User where id_user=:idAuthor";
        Query query = session.createQuery(hql);
        query.setParameter("idAuthor", id);
        String results = (String) query.getSingleResult();
        session.getTransaction().commit();
        session.close();
        return results;
    }

    public void updatePCProposalTable(List<PCProposal> pcProposal) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("DELETE FROM PCProposal");
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();

        int i = 0;
        for (PCProposal pcP : pcProposal) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            pcP.setId(i++);
            session.save(pcP);
            session.getTransaction().commit();
            session.close();
        }
    }

    public void updateUserTableOnlyPC(List<User> pcUser) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("DELETE FROM User WHERE type='pc'");
        query.executeUpdate();
        for (User user:pcUser) {
            session.save(user);
        }
        session.getTransaction().commit();
        session.close();
    }

    public void updateProposalTableOnlyPC(List<Proposal> proposalsList) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("DELETE FROM Proposal WHERE type='pc'");
        query.executeUpdate();
        for (Proposal p:proposalsList) {
            session.save(p);
        }
        session.getTransaction().commit();
        session.close();
    }
    public void UpdatePCProposal(PCProposal pcProposal) {
        PCProposal result=null;
        SessionFactory sessionFactory = (new Configuration()).configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from PCProposal where user=:user1 and proposal=:proposal1");
        query.setParameter("user1", pcProposal.getUser());
        query.setParameter("proposal1", pcProposal.getProposal());
        try {
            result = (PCProposal) query.getSingleResult();
            pcProposal.setId(result.getId());
        }
        catch (Exception e)
        {}
        if (result==null)
        {
            session.save(pcProposal);
        }
        else {
            //PCProposal pcProposal1=(PCProposal) session.merge(pcProposal);
            session.clear();
            session.update(pcProposal);
        }
        session.getTransaction().commit();
        session.close();
    }
    public boolean checkIfProposalExistReview(Proposal proposal1, User user1) {
        PCProposal PCresults = null;
        SessionFactory sessionFactory = (new Configuration()).configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from PCProposal where user=:user1 and proposal=:proposal1 and bid=1");
        query.setParameter("user1", user1);
        query.setParameter("proposal1", proposal1);

        try {
            PCresults = (PCProposal)query.getSingleResult();
        } catch (Exception var8) {
            ;
        }

        session.getTransaction().commit();
        session.close();
        return PCresults != null;
    }

    public boolean checkIfProposalExistPass(Proposal proposal1, User user1) {
        PCProposal PCresults = null;
        SessionFactory sessionFactory = (new Configuration()).configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from PCProposal where user=:user1 and proposal=:proposal1 and bid=0");
        query.setParameter("user1", user1);
        query.setParameter("proposal1", proposal1);

        try {
            PCresults = (PCProposal)query.getSingleResult();
        } catch (Exception var8) {
            ;
        }

        session.getTransaction().commit();
        session.close();
        return PCresults != null;
    }
    public int getNumberPc() {
        int nrPc=0;
        SessionFactory sessionFactory = (new Configuration()).configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("select count(*) from User where type='pc'");
        try {
            nrPc=query.getFirstResult();
        } catch (Exception var8) {
            ;
        }
        session.getTransaction().commit();
        session.close();
        return nrPc;
    }
    public List<ProposalEvalPC> getEvaluationList(int id_pc)
    {
        List<Object[]> listOb;
        List<ProposalEvalPC> list=new ArrayList<>();
        SessionFactory sessionFactory = (new Configuration()).configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        listOb =(List<Object[]>)session.createQuery("from Proposal as p inner join p.pcProps ps inner join ps.user u where u.id_user ="+id_pc).list();
        for (Object[] aRow : listOb) {
            Proposal proposal = (Proposal) aRow[0];
            PCProposal pcProposal = (PCProposal) aRow[1];
            User user=(User) aRow[2];
            ProposalEvalPC proposalEvalPC=new ProposalEvalPC(proposal,pcProposal.getReview(),pcProposal.getNota());
            list.add(proposalEvalPC);
        }

        session.getTransaction().commit();
        session.close();
//        for (Object[] o:listOb)
//        {
//            Proposal p=o[0];
//        }
        return list;
    }
}
