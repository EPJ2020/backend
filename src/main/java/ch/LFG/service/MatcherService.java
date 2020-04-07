package ch.LFG.service;

import ch.LFG.entity.Group;
import ch.LFG.entity.Match;
import ch.LFG.entity.User;
import ch.LFG.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatcherService {
    private UserService userService;
    private GroupService groupServie;

    @Autowired
    private MatchRepository matchRepository;

    private List<Match> calculateMatches(long groupId) {
        return null;
    }

    private List<Group> getMatchesForUser(long userId) {
        return null;
    }

    private List<User> getMatchesForGroup(long groupId) {
        return null;
    }

}
