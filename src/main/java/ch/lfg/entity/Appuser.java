package ch.lfg.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import org.hibernate.annotations.Proxy;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Represents a User of the application
 */
@Proxy(lazy = false)
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

  @Column(name = "loginid")
  @JsonIgnore
  private long loginId;

  @JsonIgnore
  @OneToMany
  @JoinColumn(name = "ownerid", referencedColumnName = "userid")
  List<Appgroup> groups;

  @Column(name = "lastname")
  private String lastName;
  @Column(name = "firstname")
  private String firstName;

  private String email;
  @Column(name = "phonenumber")
  private String phoneNumber;
  private String description;
  @Column(name = "isactive")
  private Boolean isActive;
  private Integer age;
  private String sex;

  @Type(type = "string-array")
  @Column(
      name = "tags",
      columnDefinition = "text[]"
  )
  private String[] tags;

  public Appuser() {
  }


  public Appuser(long userId,
                 long loginId,
                 String lastName,
                 String firstName,
                 String email,
                 String phoneNumber,
                 String description,
                 Boolean isActive,
                 Integer age,
                 String sex,
                 String[] tags) {
    this.userId = userId;
    //this.loginId = loginId;
    this.groups = groups;
    this.lastName = lastName;
    this.firstName = firstName;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.description = description;
    this.isActive = isActive;
    this.age = age;
    this.sex = sex;
    this.tags = tags;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public long getLoginId() {
    return loginId;
  }

  public void setLoginId(long loginId) {
    this.loginId = loginId;
  }

  public List<Appgroup> getGroups() {
    return groups;
  }

  public void setGroups(List<Appgroup> groups) {
    this.groups = groups;
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

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }
}
