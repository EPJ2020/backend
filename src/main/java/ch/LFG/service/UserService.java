package ch.LFG.service;

import ch.LFG.entity.Appgroup;
import ch.LFG.entity.Appuser;
import ch.LFG.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private MatcherService matcherService;
    private GroupService groupService;

    
    public Appuser getUserProfile(long id){
        return userRepository.getOne(id);
    }

    
    public Appuser setUserProfile(Appuser user) {
        userRepository.save(user);

        return userRepository.getOne(userRepository.count());
    }

    
    public Appuser updateUserProfile(Appuser user){
        userRepository.save(user);
        return userRepository.getOne(user.getUserId());
    }


    public List<Appgroup> getMyGroups(long userId) {
        return userRepository.getOne(userId).getGroups();
    }

    
    public List<Appgroup> getMatchSuggestion(long userid) {
        return null;
    }

    
    public List<Appgroup> getMyCurrentMatches(long userid) {
        return null;
    }

    
    public void setMatchAnswer(long groupId, long userId, Boolean answer){

    }

}

