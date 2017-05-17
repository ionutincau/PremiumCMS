package domain;

import javax.persistence.*;

/**
 * Created by ASUS on 17.May.2017.
 */
@Entity
public class UserSesiune {
    @Id @GeneratedValue private int id;
    @ManyToOne(cascade = CascadeType.ALL )private User user;
    @ManyToOne(cascade = CascadeType.ALL) private Sesiune sesiune;
    @Column private String functie;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Sesiune getSesiune() {
        return sesiune;
    }

    public void setSesiune(Sesiune sesiune) {
        this.sesiune = sesiune;
    }

    public String getFunctie() {
        return functie;
    }

    public void setFunctie(String functie) {
        this.functie = functie;
    }
}
