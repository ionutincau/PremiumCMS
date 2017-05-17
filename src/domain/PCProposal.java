package domain;

import javax.persistence.*;

/**
 * Created by ASUS on 17.May.2017.
 */
@Entity
public class PCProposal {
    @Id @GeneratedValue private int id;
    @ManyToOne(cascade = CascadeType.ALL )private User user;
    @ManyToOne(cascade = CascadeType.ALL) private Proposal proposal;

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
}
