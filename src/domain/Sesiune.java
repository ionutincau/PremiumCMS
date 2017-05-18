package domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by ASUS on 04.May.2017.
 */

@Entity
public class Sesiune {
    @Id @GeneratedValue(strategy = GenerationType.AUTO) private int id_session;
    @OneToMany(mappedBy = "sesiune") private Collection<UserSesiune> userSesiune = new ArrayList<UserSesiune>();
    @OneToMany(mappedBy = "sesiune") private Collection<EventSesiune> eventSesiune = new ArrayList<EventSesiune>();
    @OneToMany private Collection<Presentation> presentations=new ArrayList<Presentation>();
    @OneToMany private Collection<Proposal> proposals=new ArrayList<Proposal>();
    @Column private String name;
    @Column private int id_room;
    @OneToMany private Collection<Room> rooms=new ArrayList<Room>();
    @Column private Date date_in;
    @Column private Date date_out;
    @Column private String president;
    //@OneToOne private User president; todo

    public Sesiune(String name, int id_room, Date date_in, Date date_out, String president) {
        this.name = name;
        this.date_in = date_in;
        this.date_out = date_out;
        this.president = president;
    }

    public Sesiune() {

    }

    public int getId_session() {return id_session;}
    public void setId_session(int id_session) {this.id_session = id_session;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public Date getDate_in() {return date_in;}
    public void setDate_in(Date date_in) {this.date_in = date_in;}
    //public Collection<Presentation> getPresentations() {return presentations;}
    //public void setPresentations(Collection<Presentation> presentations) {this.presentations = presentations;}
    public Collection<Room> getRooms() {return rooms;}
    public void setRooms(Collection<Room> rooms) {this.rooms = rooms;}
    public Date getDate_out() {return date_out;}
    public void setDate_out(Date date_out) {this.date_out = date_out;}
    public Collection<UserSesiune> getUserSesiune() {return userSesiune;}
    public void setUserSesiune(Collection<UserSesiune> userSesiune) {this.userSesiune = userSesiune;}
    public Collection<EventSesiune> getEventSesiune() {return eventSesiune;}
    public void setEventSesiune(Collection<EventSesiune> eventSesiune) {this.eventSesiune = eventSesiune;}
    public String getPresident() {return president;}
    public void setPresident(String president) {this.president = president;}
    //public Collection<Proposal> getProposals() {return proposals;}
    //public void setProposals(Collection<Proposal> proposals) {this.proposals = proposals;}
    public int getId_room() {return id_room;}
    public void setId_room(int id_room) {this.id_room = id_room;}

    @Override
    public String toString() {
        return name + ",     sala: ,     " + date_in + " - " + date_out;
    }
}
