package ch.lfg.repository;

import ch.lfg.entity.Appgroup;
import ch.lfg.entity.Appuser;
import ch.lfg.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

/**
 * This is the JPArepository abstraction with CRUD methods that are getting translated into Database queries.
 */
public interface MatchRepository extends JpaRepository<Match, Long> {

  /**
   * @param user Appuserobject for which we want to recieve Matchobject
   * @param group Appgroupobject for which we want to recieve a Matchobject
   * @return Returns Matchobject with the given appuser and appgroup combination
   */
  @Query("SELECT m FROM Match m WHERE m.user = ?1 AND m.group = ?2")
  Match getMatchByUserAndGroup(Appuser user, Appgroup group);

  /**
   * @param group Identifies the appgroup for which we want to retrieve a list of their confirmed matches
   * @return Returns a list of Matches for the specified appgroup, where the appuser and the appgroup have already accepted each other
   */
  @Query("SELECT m.user FROM Match m WHERE m.group = ?1 AND m.userAccept = true AND m.groupAccept = true")
  List<Appuser> findMatchesForGroup(Appgroup group);

  /**
   * @param user Identifies the appuser for which we want to retrieve a list of their confirmed matches
   * @return Returns a list of Matches for the specified appuser, where the appgroup and the appuser have already accepted each other
   */
  @Query("SELECT m.group FROM Match m WHERE m.user = ?1 AND m.userAccept = true AND m.groupAccept = true")
  List<Appgroup> findMatchesForUser(Appuser user);

  /**
   * @param group Specifies the appuser for which we want to retrieve fitting match suggestions
   * @return returns a list of appusers, which are active and have not already swiped by the appgroup
   */
  @Query("SELECT m.user FROM Match m WHERE m.group = ?1 AND m.groupAccept is null AND m.user.isActive = true")
  List<Appuser> getSuggestionsForGroup(Appgroup group);

  /**
   * @param user Specifies the appgroup for which we want to retrieve fitting match suggestions
   * @return returns a list of appgroups, which are active and have not already swiped by the appuser
   */
  @Query("SELECT m.group FROM Match m WHERE m.user = ?1 AND m.userAccept is null AND m.group.isActive = true")
  List<Appgroup> getSuggestionsForUser(Appuser user);

}
