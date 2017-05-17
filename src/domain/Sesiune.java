package domain;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by ASUS on 04.May.2017.
 */

@Entity
public class Sesiune {
    @Id @GeneratedValue(strategy = GenerationType.AUTO) private int id_session;
    @Column private String name;
    @Column private int id_room;
    @Column private Date date_in;
    @Column private Date date_out;
    @Column private String president;

    public Sesiune(String name, int id_room, Date date_in, Date date_out, String president) {
        this.name = name;
        this.id_room = id_room;
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
    public int getId_room() {return id_room;}
    public void setId_room(int id_room) {this.id_room = id_room;}
    public Date getDate_in() {return date_in;}
    public void setDate_in(Date date_in) {this.date_in = date_in;}
    public Date getDate_out() {return date_out;}
    public void setDate_out(Date date_out) {this.date_out = date_out;}
    public String getPresident() {return president;}
    public void setPresident(String president) {this.president = president;}

    @Override
    public String toString() {
        return name + ",     sala: " + id_room + ",     " + date_in + " - " + date_out;
    }
}
