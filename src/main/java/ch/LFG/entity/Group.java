package ch.LFG.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vladmihalcea.hibernate.type.array.ListArrayType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.List;


@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "group")
@TypeDef(
        name = "list-array",
        typeClass = ListArrayType.class
)
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long groupId;

    @OneToOne(optional=false)
    @JoinColumn(name="userid")
    private User owner;

    private String name;
    private String description;
    @Column(name="isactive")
    private Boolean isActive;

    // See https://mvnrepository.com/artifact/com.vladmihalcea/hibernate-types-52
    @Type(type = "list-array" )
    @Column(
            name = "tags",
            columnDefinition = "text[]"
    )
    private List<String> tags;

    @OneToMany(mappedBy = "group")
    List<Match> matches;

    public Group() {}

    public Group(long groupId, User owner, String name, String description, Boolean isActive, List<String> tags) {
        this.groupId = groupId;
        this.owner = owner;
        this.name = name;
        this.description = description;
        this.isActive = isActive;
        this.tags = tags;

    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }
}
