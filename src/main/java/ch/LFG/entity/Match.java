package ch.LFG.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

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
    private GroupProfil group;

    @Column(columnDefinition = "boolean default null")
    private Boolean userAccept;
    @Column(columnDefinition = "boolean default null")
    private Boolean groupAccept;

    public Match() {}
    public Match(User user, GroupProfil group) {
        this.user = user;
        this.group = group;
        this.userAccept = null;
        this.groupAccept = null;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public GroupProfil getGroup() {
        return group;
    }

    public void setGroup(GroupProfil group) {
        this.group = group;
    }

    public Boolean getUserAccept() {
        return userAccept;
    }

    public void setUserAccept(Boolean userAccept) {
        this.userAccept = userAccept;
    }

    public Boolean getGroupAccept() {
        return groupAccept;
    }

    public void setGroupAccept(Boolean groupAccept) {
        this.groupAccept = groupAccept;
    }
}
