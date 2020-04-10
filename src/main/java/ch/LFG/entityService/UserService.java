package ch.LFG.entityService;

import ch.LFG.entity.Group;
import ch.LFG.entity.SocialSkill;
import ch.LFG.entity.SocialSkillRating;
import ch.LFG.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private MatchService matchService;

    @Autowired
    private JpaRepository<User, Long> userRepository;

    @Autowired
    private JpaRepository<SocialSkill, Long> socialSkillRepository;

    @Autowired
    private JpaRepository<SocialSkillRating, Long> socialSkillRatingRepository;

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User getUserProfil(User user) {
        return null;
    }

    public void setUserProfil(User user) {

    }

    public void updateUserProfil(User user) {

    }

    public List<Group> getAllGroups(User user) {
        return null;
    }

    public List<Group> getPossibleMatches(User user) {
        return null;
    }

    public List<Group> getMatches(User user) {
        return null;
    }
}

