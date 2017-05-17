package domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by ASUS on 04.May.2017.
 */

@Entity
public class Proposal {
    @Id @GeneratedValue(strategy = GenerationType.AUTO) private int id_proposal;
    @OneToMany(mappedBy = "proposal") private Collection<PCProposal> pcProps = new ArrayList<PCProposal>();
    @OneToMany private Collection<User> users = new ArrayList<User>();
    @Column private int id_author;
    @Column private String other_authors;
    @Column private String name;
    @Column private String keywords;
    @Column private String topics;
    @Column private String type;
    @Column private Date send_date;
    @Column private Date accept_date;
    @Column private String status;
    @Column private String abs;
    @Column private String document;
    @Column private int id_session;

    public Proposal() {

    }

    public Proposal(int id_author, String other_authors, String name, String keywords, String topics, String type, Date send_date, Date accept_date, String status, String abs, String document, int id_session) {
        this.id_author = id_author;
        this.other_authors = other_authors;
        this.name = name;
        this.keywords = keywords;
        this.topics = topics;
        this.type = type;
        this.send_date = send_date;
        this.accept_date = accept_date;
        this.status = status;
        this.abs = abs;
        this.document = document;
        this.id_session = id_session;
    }

    public int getId_proposal() {
        return id_proposal;
    }

    public void setId_proposal(int id_proposal) {
        this.id_proposal = id_proposal;
    }

    public Collection<PCProposal> getPCProps() {return pcProps;}

    public void setPCProps(Collection<PCProposal> pcProps) {this.pcProps = pcProps;}

    public int getId_author() {
        return id_author;
    }

    public void setId_author(int id_author) {
        this.id_author = id_author;
    }

    public String getOther_authors() {
        return other_authors;
    }

    public void setOther_authors(String other_authors) {
        this.other_authors = other_authors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getTopics() {
        return topics;
    }

    public void setTopics(String topics) {
        this.topics = topics;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getSend_date() {
        return send_date;
    }

    public void setSend_date(Date send_date) {
        this.send_date = send_date;
    }

    public Date getAccept_date() {
        return accept_date;
    }

    public void setAccept_date(Date accept_date) {
        this.accept_date = accept_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAbs() {
        return abs;
    }

    public void setAbs(String abs) {
        this.abs = abs;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public int getId_session() {
        return id_session;
    }

    public void setId_session(int id_session) {
        this.id_session = id_session;
    }
}
