package domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class User {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)  private int id_user;
    @OneToOne private Payment payment;
    @OneToMany(mappedBy = "user") private Collection<PCEvent> pcEvent = new ArrayList<PCEvent>();
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user") private Collection<PCProposal> pcProps = new ArrayList<PCProposal>();
    @OneToMany(mappedBy = "user") private Collection<UserSesiune> userSesiune = new ArrayList<UserSesiune>();
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
    @Column private String webPage;

    public User(){}


    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public Payment getPayment() {return payment;}

    public void setPayment(Payment payment) {this.payment = payment;}

    public Collection<PCProposal> getPcProps() {
        return pcProps;
    }

    public void setPcProps(Collection<PCProposal> pcProps) {
        this.pcProps = pcProps;
    }

    public Collection<UserSesiune> getUserSesiune() {
        return userSesiune;
    }

    public void setUserSesiune(Collection<UserSesiune> userSesiune) {
        this.userSesiune = userSesiune;
    }

    public Collection<PCEvent> getPcEvent() {return pcEvent;}

    public void setPcEvent(Collection<PCEvent> pcEvent) {this.pcEvent = pcEvent;}

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