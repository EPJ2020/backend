package ch.LFG.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;


@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "userprofil")
public class UserProfil {

    @Id
    @OneToOne(optional=false)
    @JoinColumn(name="id")
    @Column(name="userid")
    private User user;

    @Column(name="lastname")
    private String lastName;
    @Column(name="firstname")
    private String firstName;
    private String description;
    @Column(name="isactive")
    private boolean isActive;
    private char[] tags= new char[20];

    public UserProfil() {}

    public UserProfil(User user, String lastName, String firstName, String description, boolean isActive, char[] tags) {
        this.user = user;
        this.lastName = lastName;
        this.firstName = firstName;
        this.description = description;
        this.isActive = isActive;
        this.tags = tags;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public char[] getTags() {
        return tags;
    }

    public void setTags(char[] tags) {
        this.tags = tags;
    }


}
