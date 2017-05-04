package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by ASUS on 04.May.2017.
 */
@Entity
public class Room {
    @Id private int id_room;
    @Column private String name;
    @Column private String location;
    @Column private int nr_sits;

    public int getId_room() {return id_room;}
    public void setId_room(int id_room) {this.id_room = id_room;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getLocation() {return location;}
    public void setLocation(String location) {this.location = location;}
    public int getNr_sits() {return nr_sits;}
    public void setNr_sits(int nr_sits) {this.nr_sits = nr_sits;}

}
