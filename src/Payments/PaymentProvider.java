package Payments;

import javax.persistence.Query;

import Login.LoginController;
import domain.Payment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Date;
import java.util.List;

/**
 * Created by Aurelian on 5/19/2017.
 */
public class PaymentProvider {
    int userID = LoginController.getInstance().getUser().getId_user();

    public List<Payment> getAllPayments()
    {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Payment> payments = session.createQuery("from Payment").getResultList();
        session.getTransaction().commit();
        session.close();
        return payments;
    }

    public List<Payment> getPaymentsFromDate(Date givenDate)
    {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Payment where date=:givenDate");
        query.setParameter("givenDate", givenDate);
        List<Payment> payments = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return payments;
    }

    public void insert(Payment payment)
    {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(payment);
        session.getTransaction().commit();
        session.close();
    }

    public void update(Payment payment)
    {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(payment);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(Payment payment)
    {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(payment);
        session.getTransaction().commit();
        session.close();
    }

    public int getLastPaymentId()
    {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("SELECT MAX(id_payment) FROM Payment");
        int id = (int)query.getSingleResult();
        session.getTransaction().commit();
        session.close();
        return id;
    }

    public void updateUserPaymentId()
    {
        int lastPayment = getLastPaymentId();
        //LoginController.getInstance().getUser().setPayment(payment);

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("UPDATE User SET payment=:lastPayment WHERE id_user=:userID");
        query.setParameter("lastPayment",lastPayment);
        query.setParameter("userID",userID);
        session.getTransaction().commit();
        session.close();
    }
}
