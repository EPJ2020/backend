package ch.LFG.service;

import ch.LFG.entity.Appgroup;
import ch.LFG.entity.Appuser;
import ch.LFG.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    private MatcherService matchService;
    private UserContactService userContactService;

    @Async
    public CompletableFuture<Appgroup> getGroupProfile(long groupId) {
        return CompletableFuture.completedFuture(groupRepository.getOne(groupId));
    }

    @Async
    public CompletableFuture<Appgroup> setGroupProfile(Appgroup group) {
        groupRepository.save(group);
        return CompletableFuture.completedFuture(groupRepository.getOne(group.getGroupId()));
    }

    @Async
    public CompletableFuture<Appgroup> updateGroupProfile(Appgroup group) {
        groupRepository.save(group);
        return CompletableFuture.completedFuture(groupRepository.getOne(group.getGroupId()));
    }

    @Async
    public void deleteGroupProfile(Appgroup group) {
        groupRepository.delete(group);
    }

    @Async
    public CompletableFuture<List<Appuser>> getMatchSuggestions(long groupId) {
        return null;
    }

    @Async
    public CompletableFuture<List<Appuser>> getMyCurrentMatches(long groupId) {
        return null;
    }

    @Async
    public void setMatchAnswer(long groupId, long userId, Boolean answer){

    }

}
