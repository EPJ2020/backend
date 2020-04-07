package ch.LFG.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vladmihalcea.hibernate.type.array.ListArrayType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "user")
@TypeDef(
        name = "list-array",
        typeClass = ListArrayType.class
)
public class User {

    @Id
    @OneToOne(optional=false)
    @JoinColumn(name="id")
    @Column(name="userid")
    private Login user;

    @Column(name="lastname")
    private String lastName;
    @Column(name="firstname")
    private String firstName;
    private String description;
    @Column(name="isactive")
    private Boolean isActive;

    @Type(type = "list-array" )
    @Column(
            name = "tags",
            columnDefinition = "text[]"
    )
    private List<String> tags;

    public User() {}

    public User(Login user, String lastName, String firstName, String description, Boolean isActive, List<String> tags) {
        this.user = user;
        this.lastName = lastName;
        this.firstName = firstName;
        this.description = description;
        this.isActive = isActive;
        this.tags = tags;
    }

    public Login getUser() {
        return user;
    }

    public void setUser(Login user) {
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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
