package domain;

import javax.persistence.*;

/**
 * Created by ASUS on 17.May.2017.
 */
@Entity
public class PCEvent {
    @Id @GeneratedValue private int id;
    @ManyToOne(cascade = CascadeType.ALL) private User user;
    @ManyToOne(cascade = CascadeType.ALL) private Event event;
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

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getFunctie() {
        return functie;
    }

    public void setFunctie(String functie) {
        this.functie = functie;
    }
}
