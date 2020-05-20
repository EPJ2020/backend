package ch.lfg.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import org.hibernate.annotations.Proxy;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Represents a Group registered in the application.
 */
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
  /**
   * Contains further information about a group.
   * Information that can not be specified through the other fields.
   */
  private String description;
  /**
   * Contains the status of a group.
   */
  @Column(name = "isactive")
  private Boolean isActive;
  private String location;

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
                  String location,
                  String[] tags) {
    this.groupId = groupId;
    this.ownerId = ownerId;
    this.name = name;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.description = description;
    this.isActive = isActive;
    this.tags = tags;
    this.location = location;
  }

  /**
   * @return groupId of the Group.
   */
  public long getGroupId() {
    return groupId;
  }

  /**
   * Change the Identifier of the group.
   * @param groupId The groups new Identifier.
   */
  public void setGroupId(long groupId) {
    this.groupId = groupId;
  }

  /**
   * @return the Identifier of the group owner
   */
  public long getOwnerId() {
    return ownerId;
  }

  /**
   * Change the Identifier of the group owner
   * @param ownerId The identifier of the groups new owner
   */
  public void setOwnerId(long ownerId) {
    this.ownerId = ownerId;
  }

  /**
   * @return The name of the group
   */
  public String getName() {
    return name;
  }

  /**
   * Change the name of the group
   * @param name The new name of the group
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return The email of the group
   */
  public String getEmail() {
    return email;
  }

  /**
   * Change the email address of the group
   * @param email The new email address of the group
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * @return The phone number of the group
   */
  public String getPhoneNumber() {
    return phoneNumber;
  }

  /**
   * Change the phone number of the group
   * @param phoneNumber The new phone number of the group
   */
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  /**
   * @return The description of the group
   */
  public String getDescription() {
    return description;
  }

  /**
   * Change the groups description
   * @param description The new description of the group
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * @return The status of the group
   */
  public Boolean getActive() {
    return isActive;
  }

  /**
   * Change the status of the group
   * @param active The new status of the group
   */
  public void setActive(Boolean active) {
    isActive = active;
  }

  /**
   * @return The tags of the group
   */
  public String[] getTags() {
    return tags;
  }

  /**
   * Change the tags of the group
   * @param tags The new tags of the group
   */
  public void setTags(String[] tags) {
    this.tags = tags;
  }

  /**
   * @return The location of the group
   */
  public String getLocation() {
    return location;
  }

  /**
   * Change the location of the group
   * @param location The new location of the group
   */
  public void setLocation(String location) {
    this.location = location;
  }
}
