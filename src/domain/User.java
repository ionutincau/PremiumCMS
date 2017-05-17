package domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class User {

    @Id @GeneratedValue(strategy = GenerationType.AUTO) private int userId;
    @Column @OneToMany private Collection<Payment> payment = new ArrayList<>();
    @ManyToMany private Collection<Proposal> proposals = new ArrayList();
    @Column private String userName;
    @Column private String password;
    @Column private String lastName;
    @Column private String firstName;
    @Column private String type;
    @Column private String status;
    @Column private String country;
    @Column private String affiliation;
    @Column private String email;
    @Column private String phone;
    @Column String webPage;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Collection<Proposal> getProposals() {
        return proposals;
    }

    public void setProposals(Collection<Proposal> proposals) {
        this.proposals = proposals;
    }

    public Collection<Payment> getPayment() {return payment;}

    void setPayment(Collection<Payment> payment) {this.payment = payment;}

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebPage() {
        return webPage;
    }

    public void setWebPage(String webPage) {
        this.webPage = webPage;
    }

    @Override
    public String toString() {
        return "    " + type + "    " + firstName + "    " + lastName + "    " + country + "    " + affiliation;
    }
}