package ch.LFG.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity(name = "UserContact")
@Table(name = "usercontact")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class UserContact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usercontactid")
    private long userContactId;

    @OneToOne
    @JoinColumn(name = "userid")
    private Appuser user;

    private String email;
    @Column(name = "phonenumber")
    private String phoneNumber;

    public UserContact() {
    }

    public UserContact(long userContactId, Appuser user, String email, String phoneNumber) {
        this.userContactId = userContactId;
        this.user = user;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public long getUserContactId() {
        return userContactId;
    }

    public void setUserContactId(long userContactId) {
        this.userContactId = userContactId;
    }

    public Appuser getUser() {
        return user;
    }

    public void setUser(Appuser user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
