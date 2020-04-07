package ch.LFG.service;

import ch.LFG.entity.Group;
import ch.LFG.entity.User;
import ch.LFG.repository.SocialSkillRatingRepository;
import ch.LFG.repository.SocialSkillRepository;
import ch.LFG.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private MatchService matchService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SocialSkillRepository socialSkillRepository;

    @Autowired
    private SocialSkillRatingRepository socialSkillRatingRepository;

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User getUserProfil(long userId) {
        return null;
    }

    public void setUserProfil() {

    }

    public void updateUserProfil() {

    }

    public List<Group> getAllGroups(long userId) {
        return null;
    }

    public List<Group> getPossibleMatches(long userId) {
        return null;
    }

    public List<Group> getMatches(long userId) {
        return null;
    }
}

