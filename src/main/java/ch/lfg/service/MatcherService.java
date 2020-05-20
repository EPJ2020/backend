package ch.lfg.service;

import ch.lfg.entity.Appgroup;
import ch.lfg.entity.Appuser;
import ch.lfg.entity.Match;
import ch.lfg.entity.MatchResponse;
import ch.lfg.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Provides all core functionality for Matches
 */
@Service
public class MatcherService {

  @Autowired
  private MatchRepository matchRepository;

  @Autowired
  private UserService userService;

  @Autowired
  private GroupService groupService;

  /**
   * Initializes the database with all potential matches
   * for a group or an user.
   * @param entity An user or a group that is newly created
   * @param <T> can be Appgroup or Appuser
   */
  public <T> void initializeAllMatchSuggestion(T entity) {
    if (entity.getClass() == Appuser.class) {
      Appuser user = (Appuser) entity;

      List<Appgroup> groups = groupService.getAllGroups();
      for (Appgroup group : groups) {
        if(group.getOwnerId() != ((Appuser) entity).getUserId()) {
          Match suggestion = new Match(user, group, null, null);
          matchRepository.save(suggestion);
        }
      }

    } else if (entity.getClass() == Appgroup.class) {
      Appgroup group = (Appgroup) entity;

      List<Appuser> users = userService.getAllUsers();
      for (Appuser user : users) {
        if(((Appgroup) entity).getOwnerId() != user.getUserId()) {
          Match suggestion = new Match(user, group, null, null);
          matchRepository.save(suggestion);
        }
      }

    } else {

      throw new NullPointerException();

    }
  }

  /**
   * Compares the provide entity to the database and calculates
   * match suggestions for that entity
   * @param entity a group or an user to calculate suggestions
   * @param <T> Can be Appgroup or Appuser
   * @return a list of MatchResponse for the provided entity
   */
  public <T> List<MatchResponse> calculateSuggestions(T entity) {

    List<MatchResponse> result = new ArrayList<>();

    if (entity.getClass() == Appuser.class) {
      Appuser user = (Appuser) entity;
      List<Appgroup> groups = matchRepository.getSuggestionsForUser(user);
      for (Appgroup group : groups) {

        if (shareElement(group.getTags(), user.getTags())) {
          MatchResponse suggestion = new MatchResponse(group, calculatePercentage(user.getTags(), group.getTags()));
          result.add(suggestion);
        }
      }

    } else if (entity.getClass() == Appgroup.class) {
      Appgroup group = (Appgroup) entity;
      List<Appuser> users = matchRepository.getSuggestionsForGroup(group);
      for (Appuser user : users) {

        if (shareElement(user.getTags(), group.getTags())) {
          MatchResponse suggestion = new MatchResponse(user, calculatePercentage(group.getTags(), user.getTags()));
          result.add(suggestion);
        }
      }

    } else {
      throw new NullPointerException();
    }

    return result;

  }

  /**
   * Compares two arrays for a shared element
   * @param a array of tags to compare
   * @param b array of tags to compare
   * @return if the two lists share an element
   */
  private boolean shareElement(String[] a, String[] b) {
    Set<String> set = new HashSet<>(Arrays.asList(b));
    for (String str : a) {
      if (set.contains(str)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Compares two arrays and calculates the percentage of matching elements
   * @param myList array of tags to compare
   * @param toCompare array of tags to compare
   * @return Percentage of matching tags relative to my list
   */
  private double calculatePercentage(String[] myList, String[] toCompare) {
    Set<String> set = new HashSet<>(Arrays.asList(toCompare));
    double count = 0;
    for (String str : myList) {
      if (set.contains(str)) {
        count += 1;
      }
    }
    return count / (double)myList.length * 100;
  }

  /**
   * @param user the user to calculate matches for
   * @return a list of groups matched to the user
   */
  public List<Appgroup> getMyCurrentMatchesForUser(Appuser user) {
    return matchRepository.findMatchesForUser(user);
  }

  /**
   * @param group the group to calculate matches for
   * @return a list of users matched to the group
   */
  public List<Appuser> getMyCurrentMatchesForGroup(Appgroup group) {
    return matchRepository.findMatchesForGroup(group);
  }

  /**
   * inserts the willingness of a group or an user to participate in a match
   * @param userId The identifier of a user
   * @param groupId the identifier of a group
   * @param answer the answer of the group or user
   * @param isGroupOrUser one if the answer is for a user, two for a group
   */
  // isGroupOrUser = 1 => isUser, isGroupOrUser = 2 => isGroup
  public void setMatchAnswer(Long userId, Long groupId, Boolean answer, int isGroupOrUser) {
    Appuser user = userService.getUserProfile(userId);
    Appgroup group = groupService.getGroupProfile(groupId);
    Match result = matchRepository.getMatchByUserAndGroup(user, group);

    if (isGroupOrUser == 1) {
      result.setUserAccept(true);
    } else if (isGroupOrUser == 2) {
      result.setGroupAccept(true);
    } else {
      throw new InvalidParameterException();
    }
    matchRepository.save(result);
  }

}