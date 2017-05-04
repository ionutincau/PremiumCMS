package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by ASUS on 04.May.2017.
 */
@Entity
public class Event {

    @Id private int id_event;
    @Column private String name;
    @Column private String start;
    @Column private String end;
    @Column private String site;
    @Column private String location;
    @Column private String description;
    @Column private int id_abstract;
    @Column private int id_proposal;
    @Column private int id_evaluation;
    @Column private int id_taxes;

    public int getId_event() {return id_event;}
    public String getName() {return name;}
    public String getStart() {return start;}
    public String getEnd() {return end;}
    public String getSite() {return site;}
    public String getLocation() {return location;}

    public String getDescription() {return description;}
    public int getId_abstract() {return id_abstract;}
    public int getId_proposal() {return id_proposal;}
    public int getId_evaluation() {return id_evaluation;}
    public int getId_taxes() {return id_taxes;}

    public void setId_event(int id_event) {this.id_event = id_event;}
    public void setName(String name) {this.name = name;}
    public void setStart(String start) {this.start = start;}
    public void setEnd(String end) {this.end = end;}
    public void setSite(String site) {this.site = site;}
    public void setLocation(String location) {this.location = location;}
    public void setDescription(String description) {this.description = description;}
    public void setId_abstract(int id_abstract) {this.id_abstract = id_abstract;}
    public void setId_proposal(int id_proposal) {this.id_proposal = id_proposal;}
    public void setId_evaluation(int id_evaluation) {this.id_evaluation = id_evaluation;}
    public void setId_taxes(int id_taxes) {this.id_taxes = id_taxes;}


}
