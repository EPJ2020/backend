package ch.lfg.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import org.hibernate.annotations.Proxy;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;

@Proxy(lazy = false)
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

  @Column(name = "ownerid")
  private long ownerId;

  private String name;
  private String email;
  @Column(name = "phonenumber")
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

  public Appgroup() {
  }

  public Appgroup(long groupId,
                  long ownerId,
                  String name,
                  String email,
                  String phoneNumber,
                  String description,
                  Boolean isActive,
                  String[] tags) {
    this.groupId = groupId;
    this.ownerId = ownerId;
    this.name = name;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.description = description;
    this.isActive = isActive;
    this.tags = tags;
  }

  public long getGroupId() {
    return groupId;
  }

  public void setGroupId(long groupId) {
    this.groupId = groupId;
  }

  public long getOwnerId() {
    return ownerId;
  }

  public void setOwnerId(long ownerId) {
    this.ownerId = ownerId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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
