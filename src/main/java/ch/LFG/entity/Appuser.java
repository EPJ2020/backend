package ch.LFG.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;

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

    @OneToOne
    @JoinColumn(name = "loginid")
    private Userlogin login;

    @Column(name = "lastname")
    private String lastName;
    @Column(name = "firstname")
    private String firstName;
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

    public Appuser(long userId, Userlogin login, String lastName, String firstName, String description, Boolean isActive, String[] tags) {
        this.userId = userId;
        this.login = login;
        this.lastName = lastName;
        this.firstName = firstName;
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
