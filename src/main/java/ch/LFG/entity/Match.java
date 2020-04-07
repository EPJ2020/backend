package ch.LFG.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "match")
public class Match {
    @Id
    @OneToOne(optional=false)
    @JoinColumn(name="UserId")
    @Column(name="userid")
    private User user;

    @Id
    @OneToOne(optional=false)
    @JoinColumn(name="GroupId")
    @Column(name="groupId")
    private Group group;

    @Column(columnDefinition = "boolean default null")
    private Boolean userStatus;
    @Column(columnDefinition = "boolean default null")
    private Boolean groupStatus;

    public Match() {}
    public Match(User user, Group group) {
        this.user = user;
        this.group = group;
        this.userStatus = null;
        this.groupStatus = null;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Boolean getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Boolean userStatus) {
        this.userStatus = userStatus;
    }

    public Boolean getGroupStatus() {
        return groupStatus;
    }

    public void setGroupStatus(Boolean groupStatus) {
        this.groupStatus = groupStatus;
    }
}
