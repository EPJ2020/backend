package ch.lfg.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;

/**
 * Represents the registration details of an user.
 */
@Entity(name = "Userlogin")
@Table(name = "userlogin")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Userlogin {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "loginid")
  @JsonIgnore
  private long loginId;

  @Column(name = "username")
  private String username;
  private String password;

  @OneToOne
  @JsonIgnore
  @JoinColumn(name = "loginid", referencedColumnName = "loginid")
  private Appuser user;

  public Userlogin() {
  }

  public Userlogin(long loginId, String username, String password) {
    this.loginId = loginId;
    this.username = username;
    this.password = password;
  }

  public Userlogin(String username, String password) {
    this.username = username;
    this.password = password;
  }

  /**
   * @return The identifier of the login
   */
  public long getLoginId() {
    return loginId;
  }

  /**
   * Change the identifier for the login
   * @param loginId The new identifier for the login
   */
  public void setLoginId(long loginId) {
    this.loginId = loginId;
  }

  /**
   * @return The username of the login.
   */
  public String getUsername() {
    return username;
  }

  /**
   * Change the username
   * @param username The new username for the login
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * @return returns the password of the login
   */
  public String getPassword() {
    return password;
  }

  /**
   * Change the password
   * @param password the new password for the login
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * @return The user corresponding to the login
   */
  public Appuser getUser() {
    return user;
  }

  /**
   * Change the user corresponding to the login
   * @param user The new user for  the login
   */
  public void setUser(Appuser user) {
    this.user = user;
  }

}
