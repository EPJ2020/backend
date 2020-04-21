package ch.LFG.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity(name = "Match")
@Table(name = "match")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="matchid")
    private long matchId;

    @ManyToOne
    @JoinColumn(name = "userid")
    private Appuser user;
    @ManyToOne
    @JoinColumn(name ="groupid")
    private Appgroup group;

    @Column(name = "groupaccept")
    private Boolean groupAccept;
    @Column(name ="useraccept")
    private Boolean userAccept;

    public Match() {
    }

    public Match(long matchId, Appuser user, Appgroup group, Boolean groupAccept, Boolean userAccept) {
        this.matchId = matchId;
        this.user = user;
        this.group = group;
        this.groupAccept = groupAccept;
        this.userAccept = userAccept;
    }

    public long getMatchId() {
        return matchId;
    }

    public void setMatchId(long matchId) {
        this.matchId = matchId;
    }

    public Appuser getUser() {
        return user;
    }

    public void setUser(Appuser user) {
        this.user = user;
    }

    public Appgroup getGroup() {
        return group;
    }

    public void setGroup(Appgroup group) {
        this.group = group;
    }

    public Boolean getGroupAccept() {
        return groupAccept;
    }

    public void setGroupAccept(Boolean groupAccept) {
        this.groupAccept = groupAccept;
    }

    public Boolean getUserAccept() {
        return userAccept;
    }

    public void setUserAccept(Boolean userAccept) {
        this.userAccept = userAccept;
    }
}
