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

import java.security.InvalidParameterException;
import java.util.*;

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

    public <T> void initializeAllMatchSuggestion(T entity) {
        if ( entity.getClass() == Appuser.class ) {

            List<Appgroup> groups = groupService.getAllGroups();
            for (Appgroup item : groups) {
                matchRepository.save(new Match((Appuser) entity, item, null, null));
            }

        } else if ( entity.getClass() == Appgroup.class ) {

            List<Appuser> users = userService.getAllUsers();
            for (Appuser item : users) {
                matchRepository.save(new Match( item, (Appgroup) entity, null, null));
            }

        } else  {

            throw  new NullPointerException();

        }
    }

    //TODO counter to 7 not jet implemented && initialisation of suggestions (issue Nr. 87)
    public List<Appgroup> calculateSuggestionsForUser(Appuser user) {
        List<Appgroup> groups = groupService.getAllGroups();
        List<Appgroup> result = new ArrayList<Appgroup>();
        for(Appgroup group: groups){
            if(sharesAnElement(group.getTags(), user.getTags()) && notAlreadySwipedUser(user.getUserId(), group.getGroupId()) && group.getActive()){
                result.add(group);
            }
        }
        return result;
    }

    public List<Appuser> calculateSuggestionsForGroup(Appgroup group) {
        List<Appuser> users = userService.getAllUsers();
        List<Appuser> result = new ArrayList<Appuser>();
        for(Appuser user: users){
            if(sharesAnElement(user.getTags(), group.getTags()) && notAlreadySwipedGroup(user.getUserId(), group.getGroupId()) && user.getActive()){
                result.add(user);
            }
        }
        return result;
    }

    private boolean notAlreadySwipedUser(long userId, long groupId){
        List<Match> matches = matchRepository.findAll();
        for(Match m: matches) {
            if (m.getGroup().getGroupId() == groupId && m.getUser().getUserId() == userId && m.getUserAccept() == null) {
                return true;
            }
        }
        return false;
    }

    private boolean notAlreadySwipedGroup(long userId, long groupId){
        List<Match> matches = matchRepository.findAll();
        for(Match m: matches) {
            if (m.getGroup().getGroupId() == groupId && m.getUser().getUserId() == userId && m.getGroupAccept() == null) {
                return true;
            }
        }
        return false;
    }

    private boolean sharesAnElement(String[] a, String[] b) {
        Set<String> bSet = new HashSet<>(Arrays.asList(b));
        for (String str : a) {
            if (bSet.contains(str)) {
                return true;
            }
        }
        return false;
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

    // isGroupOrUser = 1 => isUser, isGroupOrUser = 2 => isGroup
    public void setMatchAnswer(Long userId, Long groupId, Boolean answer, int isGroupOrUser) {

        matchRepository.findAll().forEach((m) -> {
            if (m.getGroup().getGroupId() == groupId && m.getUser().getUserId() == userId) {
                if (isGroupOrUser == 1) {
                    m.setUserAccept(answer);
                } else if (isGroupOrUser == 2) {
                    m.setGroupAccept(answer);
                } else {
                    throw new InvalidParameterException();
                }
                matchRepository.save(m);
                return;
            }

        });

    }
}
