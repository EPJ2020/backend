package ch.lfg.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;

@Entity(name = "Userlogin")
@Table(name = "userlogin")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Userlogin {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "loginid")
  private long loginId;

  @OneToOne
  @JoinColumn(name = "loginid", referencedColumnName = "loginid")
  private Appuser user;

  @Column(name = "username")
  private String userName;
  private String password;

  public Userlogin() {
  }

  public Userlogin(long loginId, Appuser user, String userName, String password) {
    this.loginId = loginId;
    this.user = user;
    this.userName = userName;
    this.password = password;
  }

  public long getLoginId() {
    return loginId;
  }

  public void setLoginId(long loginId) {
    this.loginId = loginId;
  }

  public Appuser getUser() {
    return user;
  }

  public void setUser(Appuser user) {
    this.user = user;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
