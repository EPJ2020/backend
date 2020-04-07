package ch.LFG.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "usercontact")
public class UserContact {

    @Id
    @OneToOne(optional=false)
    @JoinColumn(name="id")
    @Column(name="userid")
    private User user;

    @Column(name="email")
    private String eMail;
    private String telephone;

    public UserContact(){

    }

    public UserContact(User user, String eMail, String telephone){
        this.user = user;
        this.eMail = eMail;
        this.telephone = telephone;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User User) {
        this.user = user;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
