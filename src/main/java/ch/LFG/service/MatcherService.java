package ch.LFG.service;

import ch.LFG.entity.Appgroup;
import ch.LFG.entity.Appuser;
import ch.LFG.entity.Match;
import ch.LFG.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatcherService {
    private UserService userService;
    private GroupService groupServie;

    @Autowired
    private MatchRepository matchRepository;

    private List<Match> calculateMatches(Appgroup group) {
        return null;
    }

    private List<Appgroup> getMatchesForUser(Appuser user) {
        return null;
    }

    private List<Appuser> getMatchesForGroup(Appgroup group) {
        return null;
    }


}
