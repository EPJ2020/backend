package ch.LFG.entityService;

import ch.LFG.entity.Group;
import ch.LFG.entity.Match;
import ch.LFG.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchService {
    private UserService userService;
    private GroupService groupServie;

    @Autowired
    private JpaRepository<Match, Long> matchRepository;

    private List<Match> calculateMatches(Group group) {
        return null;
    }

    private List<Group> getMatchesForUser(User user) {
        return null;
    }

    private List<User> getMatchesForGroup(Group group) {
        return null;
    }

}
