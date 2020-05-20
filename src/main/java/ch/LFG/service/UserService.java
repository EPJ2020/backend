package ch.lfg.service;

import ch.lfg.entity.Appgroup;
import ch.lfg.entity.Appuser;
import ch.lfg.entity.MatchAnswer;
import ch.lfg.entity.MatchResponse;
import ch.lfg.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Provides all core functionality for Users
 */
@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private MatcherService matcherService;

  /**
   * @param id This is the database identifier of the appuser we want to retrieve
   * @return Returns an appuser with the given identifier
   * @throws javax.persistence.EntityNotFoundException gets thrown if there is no Object with the given identifier stored in the database
   */
  public Appuser getUserProfile(long id) {
    return userRepository.getOne(id);
  }

  /**
   * @param user This is the appuser that was recieved from a client, with details that should be saved in the database
   * @return Retrieves the freshly updated database entry and returns it as confirmation
   * @throws NullPointerException gets thrown if there already exists an object with the given appuser's database identifier.
   */
  public Appuser setUserProfile(Appuser user) {
    Appuser tempuser = userRepository.save(user);
    matcherService.initializeAllMatchSuggestion(tempuser);
    return tempuser;
  }

  /**
   * @param user This is the appuser that was recieved from a client, with details that should be saved in the database
   * @return Retrieves the freshly updated database entry and returns it as confirmation
   * @throws NullPointerException gets thrown if there is no object with the given appuser's database identifier.
   */
  public Appuser updateUserProfile(Appuser user) {
    try {
      var existinguser = userRepository.getOne(user.getUserId());
      user.setGroups(existinguser.getGroups());
      return userRepository.save(user);
    } catch (Exception e) {
      throw new NullPointerException("User doesn't exist");
    }
  }


  /**
   * Provides all group where the user is owner
   * @param userId id of the owner of the group
   * @return List of groups
   */
  public List<Appgroup> getMyGroups(long userId) {
    Appuser user = userRepository.getOne(userId);
    return user.getGroups();
  }

  /**
   * Provides a list of suggestions for matches for the specified user
   * @param userid Specifies the database identifier of the object for which we want to retrieve Match suggestions
   * @return returns a matchresponse object that contains either a user or a group, as well as a percentage number as a double
   */
  public List<MatchResponse> getMatchSuggestion(long userid) {
    Appuser user = userRepository.getOne(userid);
    return matcherService.calculateSuggestions(user);
  }

  /**
   * Provides a List of all the matches the user has
   * @param user user for which the matches are to be found
   * @return returns a list of appuserobjects, where the group and the user have accepted each other.
   */
  public List<Appgroup> getMyCurrentMatches(Appuser user) {
    return matcherService.getMyCurrentMatchesForUser(user);
  }

  /**
   * Sets the Answer for a match for this user
   * @param match Needs to be a matchanswer object, containing the respetive user and group object, as well as a boolean acting as the swipe answer
   * @return returns a boolean that confirms if the operation was successful
   */
  public Boolean setMatchAnswer(MatchAnswer match) {
    try {
      matcherService.setMatchAnswer(match.getUserId(), match.getGroupId(), match.getAnswer(), 1);
      return true;
    } catch (Exception e) {
      return false;
    }

  }

  public List<Appuser> getAllUsers() {
    return userRepository.findAll();
  }

  public Appuser getUserByLoginId(Long id) {
    return userRepository.getUserByLoginId(id);
  }

}
