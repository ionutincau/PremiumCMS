package domain;

import javax.persistence.*;

/**
 * Created by ASUS on 04.May.2017.
 */
@Entity
public class Presentation {
    @Id @GeneratedValue(strategy = GenerationType.AUTO) private int id_presentation;
    @OneToOne private User speaker;
    @Column private String demo;
    @Column private String name;

    public int getId_presentation() {return id_presentation;}
    public void setId_presentation(int id_presentation) {this.id_presentation = id_presentation;}
    public String getDemo() {return demo;}
    public void setDemo(String demo) {this.demo = demo;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public User getSpeaker() {return speaker;}
    public void setSpeaker(User speaker) {this.speaker = speaker;}

}
