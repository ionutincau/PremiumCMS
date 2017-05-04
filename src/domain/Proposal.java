package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by ASUS on 04.May.2017.
 */
@Entity
public class Proposal {
    @Id private int id_proposal;
    @Column private String name;
    @Column private String keywwords;
    @Column private String topics;
    @Column private String date;

    public int getId_proposal() {return id_proposal;}
    public String getName() {return name;}
    public String getKeywwords() {return keywwords;}
    public String getTopics() {return topics;}
    public String getDate() {return date;}

    public void setId_proposal(int id_proposal) {this.id_proposal = id_proposal;}
    public void setName(String name) {this.name = name;}
    public void setKeywwords(String keywwords) { this.keywwords = keywwords; }
    public void setTopics(String topics) { this.topics = topics; }
    public void setDate(String date) { this.date = date; }
}
