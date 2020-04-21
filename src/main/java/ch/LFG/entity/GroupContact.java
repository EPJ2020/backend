package ch.LFG.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity(name = "GroupContact")
@Table(name = "groupcontact")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class GroupContact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "groupcontactid")
    private long groupContactId;

    @OneToOne
    @JoinColumn(name = "groupid")
    private Appgroup group;

    private String email;
    @Column(name = "phonenumber")
    private String phoneNumber;

    public GroupContact() {
    }

    public GroupContact(long groupContactId, Appgroup group, String email, String phoneNumber) {
        this.groupContactId = groupContactId;
        this.group = group;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public long getGroupContactId() {
        return groupContactId;
    }

    public void setGroupContactId(long groupContactId) {
        this.groupContactId = groupContactId;
    }

    public Appgroup getGroup() {
        return group;
    }

    public void setGroup(Appgroup group) {
        this.group = group;
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
