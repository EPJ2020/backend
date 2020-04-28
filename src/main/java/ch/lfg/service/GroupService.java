package ch.lfg.service;

import ch.lfg.entity.Appgroup;
import ch.lfg.entity.Appuser;
import ch.lfg.entity.MatchAnswer;
import ch.lfg.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GroupService {

  @Autowired
  private GroupRepository groupRepository;

  @Autowired
  private MatcherService matcherService;

  public Appgroup getGroupProfile(long groupId) {
    return groupRepository.getOne(groupId);
  }

  public Appgroup setGroupProfile(Appgroup group) {
    try {
      groupRepository.getOne(group.getGroupId());
    } catch (Exception e) {
      Appgroup tempgroup = groupRepository.save(group);
      matcherService.initializeAllMatchSuggestion(tempgroup);
      return tempgroup;
    }
    throw new NullPointerException("UserId already exists");
  }

  public Appgroup updateGroupProfile(Appgroup group) {
    try {
      Appgroup group2 = groupRepository.getOne(group.getGroupId());
    } catch (Exception e) {
      throw new NullPointerException("User doesn't exist");
    }
    return groupRepository.save(group);
  }

  public Boolean deleteGroupProfile(long groupId) {
    try {
      groupRepository.delete(groupRepository.getOne(groupId));
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public List<Appuser> getMatchSuggestions(long id) {
    return matcherService.calculateSuggestionsForGroup(groupRepository.getOne(id));
  }

  public List<Appuser> getMyCurrentMatches(long id) {
    return matcherService.getMyCurrentMatchesForGroup(id);
  }

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
