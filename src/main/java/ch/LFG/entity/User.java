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
    @Column(name = "userid")
    private long userId;

    @OneToOne//(optional=false)
    @PrimaryKeyJoinColumn(name="userid", referencedColumnName="userid")
    private Login login;

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

    @OneToMany(mappedBy = "user")
    List<Match> matches;

    @OneToMany(mappedBy = "socialSkill")
    List<SocialSkillRating> ratings;

    public User() {}

    public User(Login login, String lastName, String firstName, String description, Boolean isActive, List<String> tags) {
        this.login = login;
        this.lastName = lastName;
        this.firstName = firstName;
        this.description = description;
        this.isActive = isActive;
        this.tags = tags;
    }

    public Login getUser() {
        return login;
    }

    public void setUser(Login login) {
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

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public List<SocialSkillRating> getRatings() {
        return ratings;
    }

    public void setRatings(List<SocialSkillRating> ratings) {
        this.ratings = ratings;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
