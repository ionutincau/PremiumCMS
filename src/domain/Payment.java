package domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by ASUS on 04.May.2017.
 */
@Entity
public class Payment {
    @Id @GeneratedValue(strategy = GenerationType.AUTO) private int id_payment;
    @Column private Date date;



    public int getId_payment() {return id_payment;}
    public void setId_payment(int id_payment) {this.id_payment = id_payment;}
    public Date getDate() {return date;}
    public void setDate(Date date) {this.date = date;}

}
