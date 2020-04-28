package ch.lfg.service;

import ch.lfg.entity.Appgroup;
import ch.lfg.entity.Appuser;
import ch.lfg.entity.MatchAnswer;
import ch.lfg.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private MatcherService matcherService;

  @Autowired
  private GroupService groupService;


  public Appuser getUserProfile(long id) {
    return userRepository.getOne(id);
  }


  public Appuser setUserProfile(Appuser user) {
    try {
      userRepository.getOne(user.getUserId());
    } catch (Exception e) {
      Appuser tempuser = userRepository.save(user);
      matcherService.initializeAllMatchSuggestion(tempuser);
      return tempuser;
    }
    throw new NullPointerException("UserId already exists");
  }


  public Appuser updateUserProfile(Appuser user) {
    return userRepository.save(user);
  }


  public List<Appgroup> getMyGroups(long userId) {
    return userRepository.getOne(userId).getGroups();
  }


  public List<Appgroup> getMatchSuggestion(long userid) {
    return matcherService.calculateSuggestionsForUser(userRepository.getOne(userid));

  }

  public List<Appgroup> getMyCurrentMatches(long userid) {
    return matcherService.getMyCurrentMatchesForUser(userid);
  }


  public void setMatchAnswer(MatchAnswer match) {
    matcherService.setMatchAnswer(match.getUserId(), match.getGroupId(), match.getAnswer(), 1);
  }

  public List<Appuser> getAllUsers() {
    return userRepository.findAll();
  }

}
