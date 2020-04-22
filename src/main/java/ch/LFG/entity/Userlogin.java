package ch.LFG.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Entity(name = "Userlogin")
@Table(name = "userlogin")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Userlogin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loginid")
    private long loginId;
    @Column(name = "username")
    private String userName;
    private String password;

    public Userlogin() {
    }

    public Userlogin(long loginId, String userName, String password) {
        this.loginId = loginId;
        this.userName = userName;
        this.password = password;
    }

    public long getLoginId() {
        return loginId;
    }

    public void setLoginId(long userId) {
        this.loginId = userId;
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
