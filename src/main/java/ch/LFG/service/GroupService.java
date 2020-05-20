package ch.lfg.service;

import ch.lfg.entity.Appgroup;
import ch.lfg.entity.Appuser;
import ch.lfg.entity.MatchAnswer;
import ch.lfg.entity.MatchResponse;
import ch.lfg.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


/**
 * Provides all core functionality for Groups
 */
@Service
public class GroupService {

  @Autowired
  private GroupRepository groupRepository;

  @Autowired
  private MatcherService matcherService;

  /**
   * @param groupId This is the database identifier of the appgroup we want to retrieve
   * @return Returns an appgroup with the given identifier
   * @throws javax.persistence.EntityNotFoundException gets thrown if there is no Object with the given identifier stored in the database
   */
  public Appgroup getGroupProfile(long groupId) {
    return groupRepository.getOne(groupId);
  }

  /**
   * @param group This is the appgroup that was recieved from a client, with details that should be saved in the database
   * @return Retrieves the freshly updated database entry and returns it as confirmation
   * @throws NullPointerException gets thrown if there already exists an object with the given appgroup's database identifier.
   */
  public Appgroup setGroupProfile(Appgroup group) {
    try {
      groupRepository.getOne(group.getGroupId());
    } catch (Exception e) {
      Appgroup tempgroup = groupRepository.save(group);
      matcherService.initializeAllMatchSuggestion(tempgroup);
      return tempgroup;
    }
    throw new NullPointerException("GroupId already exists");
  }

  /**
   * @param group This is the appgroup that was recieved from a client, with details that should be saved in the database
   * @return Retrieves the freshly updated database entry and returns it as confirmation
   * @throws NullPointerException gets thrown if there is no object with the given appgroup's database identifier.
   */
  public Appgroup updateGroupProfile(Appgroup group) {
    try {
      Appgroup tempgroup = groupRepository.getOne(group.getGroupId());
    } catch (Exception e) {
      throw new NullPointerException("Group doesn't exist");
    }
    return groupRepository.save(group);
  }

  /**
   * @param groupId This is the database identifier of an object we want to delete from the database
   * @return returns a boolean that confirms if the operation was successful
   */
  public Boolean deleteGroupProfile(long groupId) {
    try {
      Appgroup group = groupRepository.getOne(groupId);
      groupRepository.delete(group);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Provides a list of suggestions for matches for the specified group
   * @param id Specifies the database identifier of the object for which we want to retrieve Match suggestions
   * @return returns a matchresponse object that contains either a user or a group, as well as a percentage number as a double
   */
  public List<MatchResponse> getMatchSuggestions(long id) {
    Appgroup group = groupRepository.getOne(id);
    return matcherService.calculateSuggestions(group);
  }

  /**
   * Provides a List of all the matches the group has
   * @param id Specifies the database identifier of the object for which we want to retrieve Match suggestions
   * @return returns a list of appuserobjects, where the group and the user have accepted each other.
   */
  public List<Appuser> getMyCurrentMatches(long id) {
    Appgroup group = groupRepository.getOne(id);
    return matcherService.getMyCurrentMatchesForGroup(group);
  }

  /**
   * Sets the Answer for a match for this group
   * @param match Needs to be a matchanswer object, containing the respetive user and group object, as well as a boolean acting as the swipe answer
   * @return returns a boolean that confirms if the operation was successful
   */
  public Boolean setMatchAnswer(MatchAnswer match) {
    try {
      matcherService.setMatchAnswer(match.getUserId(), match.getGroupId(), match.getAnswer(), 2);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public List<Appgroup> getAllGroups() {
    return groupRepository.findAll();
  }

}
