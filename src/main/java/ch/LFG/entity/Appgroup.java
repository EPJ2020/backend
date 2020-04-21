package ch.LFG.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;

@Entity(name = "Appgroup")
@Table(name = "appgroup")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@TypeDefs({
        @TypeDef(
                name = "string-array",
                typeClass = StringArrayType.class
        )
})
public class Appgroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "groupid")
    private long groupId;

    @ManyToOne
    @JoinColumn(name = "ownerid")
    private Appuser owner;

    private String name;
    private String description;
    @Column(name = "isactive")
    private Boolean isActive;

    @Type(type = "string-array")
    @Column(
            name = "tags",
            columnDefinition = "text[]"
    )
    private String[] tags;

    public Appgroup() {
    }

    public long getGroupId() { return groupId; }

    public void setGroupId(long groupId) { this.groupId = groupId; }

    public Appuser getOwner() {
        return owner;
    }

    public void setOwner(Appuser owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }

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
        this.tags=tags;
    }

}
