package ch.LFG.service;

import ch.LFG.entity.Appgroup;
import ch.LFG.entity.Appuser;
import ch.LFG.entity.Match;
import ch.LFG.repository.MatchRepository;

import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatcherService {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

    public List<Appuser> calculateSuggestions(Appgroup group) {
        return null;
    }

    public List<Appgroup> calculateSuggestions(Appuser user) {
        return null;
    }

    public List<Appgroup> getMyCurrentMatchesForUser(long id) {
        List<Appgroup> result = new ArrayList<Appgroup>();
        matchRepository.findAll().forEach((m) -> {
            if((m.getUser().getUserId() == id) && (m.getGroupAccept() != null && m.getGroupAccept()) && (m.getUserAccept() != null && m.getUserAccept())){
                result.add(m.getGroup());
            }
        });
        return result;
    }

    public List<Appuser> getMyCurrentMatchesForGroup(long id) {
        List<Appuser> result = new ArrayList<Appuser>();
        List<Match> matches = matchRepository.findAll();
        matches.forEach((m) -> {
            if((m.getGroup().getGroupId() == id) && (m.getGroupAccept() != null && m.getGroupAccept()) && (m.getUserAccept() != null && m.getUserAccept())){
                result.add(m.getUser());
            }
        });
        return result;
    }

    public void setMatchAnswer(long groupId, long userId, Boolean answer, int isGroupOrUser) {

    }
}
