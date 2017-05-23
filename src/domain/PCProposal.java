package domain;

import javax.persistence.*;

/**
 * Created by ASUS on 17.May.2017.
 */

@Entity
public class PCProposal {

    @Id @GeneratedValue(strategy = GenerationType.AUTO) private int id;
    @ManyToOne(cascade = CascadeType.ALL) private User user;
    @ManyToOne(cascade = CascadeType.ALL) private Proposal proposal;
    @Column private int bid;
    @Column private String nota;
    @Column private String review;

    public PCProposal() {

    }

    public PCProposal(User user, Proposal proposal) {
        this.user = user;
        this.proposal = proposal;
    }

    public PCProposal(User user, Proposal proposal, int bid, String nota, String review) {
        this.user = user;
        this.proposal = proposal;
        this.bid = bid;
        this.nota = nota;
        this.review = review;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Proposal getProposal() {
        return proposal;
    }

    public void setProposal(Proposal proposal) {
        this.proposal = proposal;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
