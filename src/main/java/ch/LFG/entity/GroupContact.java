package ch.LFG.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "groupcontact")
public class GroupContact {

    @Id
    @OneToOne(optional=false)
    @JoinColumn(name="id")
    @Column(name="groupid")
    private Group group;

    @Column(name="email")
    private String eMail;
    private String telephone;

    public GroupContact(){

    }

    public GroupContact(Group group, String eMail, String telephone){
        this.group = group;
        this.eMail = eMail;
        this.telephone = telephone;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
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
