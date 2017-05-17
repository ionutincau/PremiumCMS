package domain;

import javax.persistence.*;

/**
 * Created by ASUS on 17.May.2017.
 */
@Entity
public class EventSesiune {
    @Id @GeneratedValue private int id;
    @ManyToOne(cascade = CascadeType.ALL )private Event event;
    @ManyToOne(cascade = CascadeType.ALL) private Sesiune sesiune;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Sesiune getSesiune() {
        return sesiune;
    }

    public void setSesiune(Sesiune sesiune) {
        this.sesiune = sesiune;
    }
}
