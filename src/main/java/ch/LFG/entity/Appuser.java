package ch.LFG.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import org.hibernate.annotations.Proxy;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.hibernate.mapping.Collection;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Appuser")
@Table(name = "appuser")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@TypeDefs({
        @TypeDef(
                name = "string-array",
                typeClass = StringArrayType.class
        )
})
public class Appuser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private long userId;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "ownerid", referencedColumnName = "userid")
    List<Appgroup> groups;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "loginid")
    private Userlogin login;

    @Column(name = "lastname")
    private String lastName;
    @Column(name = "firstname")
    private String firstName;

    private String email;
    @Column(name ="phonenumber")
    private String phoneNumber;
    private String description;
    @Column(name = "isactive")
    private Boolean isActive;

    @Type(type = "string-array")
    @Column(
            name = "tags",
            columnDefinition = "text[]"
    )
    private String[] tags;

    public Appuser() {
    }

    public Appuser(long userId, List<Appgroup> groups, Userlogin login, String lastName, String firstName, String email, String phoneNumber, String description, Boolean isActive, String[] tags) {
        this.userId = userId;
        this.groups = groups;
        this.login = login;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.isActive = isActive;
        this.tags = tags;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public List<Appgroup> getGroups() {
        return groups;
    }

    public void setGroups(List<Appgroup> groups) {
        this.groups = groups;
    }

    public Userlogin getLogin() {
        return login;
    }

    public void setLogin(Userlogin login) {
        this.login = login;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }
}
