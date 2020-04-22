package ch.LFG.service;

import ch.LFG.entity.Appgroup;
import ch.LFG.entity.Match;
import ch.LFG.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class MatcherService {

    @Autowired
    private MatchRepository matchRepository;

    private UserService userService;
    private GroupService groupService;

    @Async
    public CompletableFuture<List<Match>> calculateSuggestion(Appgroup group) {
        return null;
    }

    @Async
    public CompletableFuture<List<Appgroup>> getMyCurrentMatches(long id, int isGroupOrUser) {
        return null;
    }

    @Async
    public void setMatchAnswer(long groupId, long userId, Boolean answer, int isGroupOrUser) {

    }


}
