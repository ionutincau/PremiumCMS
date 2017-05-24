package Payments;

import Login.LoginController;
import domain.Payment;

import java.sql.Date;
import java.util.Observable;

/**
 * Created by Aurelian on 5/19/2017.
 */
public class PaymentController extends Observable {

    public PaymentProvider paymentProvider = new PaymentProvider();

    public void addPayment()
    {
        Date date =  new java.sql.Date(new java.util.Date().getTime());
        Payment payment = new Payment();
        payment.setDate(date);
        paymentProvider.insert(payment);
        paymentProvider.updateUserPaymentId();
        setChanged();
        notifyObservers();
    }

    public boolean userPaid()
    {
        boolean paid = LoginController.getInstance().getUser().getPayment() != null;
        return paid;
    }
}