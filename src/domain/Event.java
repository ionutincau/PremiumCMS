package domain;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by ASUS on 04.May.2017.
 */
@Entity
public class Event {

    @Id @GeneratedValue(strategy = GenerationType.AUTO) private int id_event;
    @Column private String name;
    @Column private Date start;
    @Column private Date end;
    @Column private String web_page;
    @Column private String location;
    @Column private String description;
    @Column private Date d_abstract;
    @Column private Date d_proposal;
    @Column private Date d_evaluation;
    @Column private Date d_taxes;

    public int getId_event() {return id_event;}
    public String getName() {return name;}
    public Date getStart() {return start;}
    public Date getEnd() {return end;}
    public String getWeb_page() {return web_page;}
    public String getLocation() {return location;}

    public String getDescription() {return description;}
    public Date getD_abstract() {return d_abstract;}
    public Date getD_proposal() {return d_proposal;}
    public Date getD_evaluation() {return d_evaluation;}
    public Date getD_taxes() {return d_taxes;}

    public void setId_event(int id_event) {this.id_event = id_event;}
    public void setName(String name) {this.name = name;}
    public void setStart(Date start) {this.start = start;}
    public void setEnd(Date end) {this.end = end;}
    public void setWeb_page(String web_page) {this.web_page = web_page;}
    public void setLocation(String location) {this.location = location;}
    public void setDescription(String description) {this.description = description;}
    public void setD_abstract(Date d_abstract) {this.d_abstract = d_abstract;}
    public void setD_proposal(Date d_proposal) {this.d_proposal = d_proposal;}
    public void setD_evaluation(Date d_evaluation) {this.d_evaluation = d_evaluation;}
    public void setD_taxes(Date d_taxes) {this.d_taxes = d_taxes;}


}
