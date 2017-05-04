package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by ASUS on 04.May.2017.
 */
@Entity
public class Section {
    @Id private int id_section;
    @Column private String name;
    @Column private int id_room;
    @Column private String date_in;
    @Column private String date_out;
    @Column private String president;

    public int getId_section() {return id_section;}
    public void setId_section(int id_section) {this.id_section = id_section;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public int getId_room() {return id_room;}
    public void setId_room(int id_room) {this.id_room = id_room;}
    public String getDate_in() {return date_in;}
    public void setDate_in(String date_in) {this.date_in = date_in;}
    public String getDate_out() {return date_out;}
    public void setDate_out(String date_out) {this.date_out = date_out;}
    public String getPresident() {return president;}
    public void setPresident(String president) {this.president = president;}

}
