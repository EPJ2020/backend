package ch.lfg.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Proxy;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;


/**
 * Represents a match between a group and an user.
 */
@Proxy(lazy = false)
@Entity(name = "Match")
@Table(name = "match")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Match {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "matchid")
  private long matchId;

  @ManyToOne
  @JoinColumn(name = "userid")
  private Appuser user;
  @ManyToOne(cascade = CascadeType.REMOVE)
  @JoinColumn(name = "groupid")
  private Appgroup group;

  @Column(name = "groupaccept")
  private Boolean groupAccept;
  @Column(name = "useraccept")
  private Boolean userAccept;

  public Match() {
  }

  public Match(Appuser user, Appgroup group, Boolean groupAccept, Boolean userAccept) {
    this.user = user;
    this.group = group;
    this.groupAccept = groupAccept;
    this.userAccept = userAccept;
  }

  /**
   * @return The identifier of a match.
   */
  public long getMatchId() {
    return matchId;
  }

  /**
   * Change the Identifier of a Match
   * @param matchId The new Identifier of a match.
   */
  public void setMatchId(long matchId) {
    this.matchId = matchId;
  }

  /**
   * @return The User evolved in this match.
   */
  public Appuser getUser() {
    return user;
  }

  /**
   * Change the user evolved in this match
   * @param user New User for this match
   */
  public void setUser(Appuser user) {
    this.user = user;
  }

  /**
   * @return The group evolved in this match
   */
  public Appgroup getGroup() {
    return group;
  }

  /**
   * Change the group evolved in this match
   * @param group New Group for this match
   */
  public void setGroup(Appgroup group) {
    this.group = group;
  }

  /**
   * @return The willingness of the group for this match
   */
  public Boolean getGroupAccept() {
    return groupAccept;
  }

  /**
   * Change the status of the group accept
   * @param groupAccept New status of group accept
   */
  public void setGroupAccept(Boolean groupAccept) {
    this.groupAccept = groupAccept;
  }

  /**
   * @return The willingness of the user for this match
   */
  public Boolean getUserAccept() {
    return userAccept;
  }

  /**
   * Change the status of the user accept
   * @param userAccept New status of user accept
   */
  public void setUserAccept(Boolean userAccept) {
    this.userAccept = userAccept;
  }
}
