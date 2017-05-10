package domain;

import javax.persistence.*;

/**
 * Created by ASUS on 04.May.2017.
 */
@Entity
public class Presentation {
    @Id @GeneratedValue(strategy = GenerationType.AUTO) private int id_presentation;
    @Column private int id_section;
    @Column private String speaker;
    @Column private String demo;
    @Column private String name;

    public int getId_presentation() {return id_presentation;}
    public void setId_presentation(int id_presentation) {this.id_presentation = id_presentation;}
    public int getId_section() {return id_section;}
    public void setId_section(int id_section) {this.id_section = id_section;}
    public String getSpeaker() {return speaker;}
    public void setSpeaker(String speaker) {this.speaker = speaker;}
    public String getDemo() {return demo;}
    public void setDemo(String demo) {this.demo = demo;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

}
