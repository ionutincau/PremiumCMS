package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by ASUS on 04.May.2017.
 */
@Entity
public class Payment {
    @Id private int id_payments;
    @Column private int id_user;
    @Column private String date;

    public int getId_payments() {return id_payments;}
    public void setId_payments(int id_payments) {this.id_payments = id_payments;}
    public int getId_user() {return id_user;}
    public void setId_user(int id_user) {this.id_user = id_user;}
    public String getDate() {return date;}
    public void setDate(String date) {this.date = date;}

}
