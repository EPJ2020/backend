package ch.LFG.service;

import ch.LFG.entity.Appgroup;
import ch.LFG.entity.Appuser;
import ch.LFG.entity.Match;

import ch.LFG.repository.MatchRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatcherService {

    @Autowired
    private MatchRepository matchRepository;

    private UserService userService;
    private GroupService groupServie;

    @Async
    public List<Match> calculateMatches(Appgroup group) {
        return null;
    }

    @Async
    public List<Appgroup> getMatchesForUser(Appuser user) {
        return null;
    }

    @Async
    public List<Appuser> getMatchesForGroup(Appgroup group) {
        return null;
    }


}
