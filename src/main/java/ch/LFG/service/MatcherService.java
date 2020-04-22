package ch.LFG.service;

import ch.LFG.entity.Appgroup;
import ch.LFG.entity.Match;
import ch.LFG.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatcherService {

    @Autowired
    private MatchRepository matchRepository;

    private UserService userService;
    private GroupService groupService;

    public List<Match> calculateSuggestion(Appgroup group) {
        return null;
    }

    public List<Appgroup> getMyCurrentMatches(long id, int isGroupOrUser) {
        return null;
    }

    public void setMatchAnswer(long groupId, long userId, Boolean answer, int isGroupOrUser) {

    }
}
