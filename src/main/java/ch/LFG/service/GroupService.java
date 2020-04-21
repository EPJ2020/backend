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
    public Appgroup getGroupProfil(Appgroup group) {
        return null; //groupRepository.findById(groupRepository.getOne(group));
    }

    @Async
    public void setGroupProfil(Appgroup group) {
        groupRepository.save(group);
    }

    @Async
    public Appgroup updateGroupProfil(Appgroup group, long id) {
        groupRepository.save(group);
        return groupRepository.getOne(id);
    }

    @Async
    public void deleteGroupProfil(Appgroup group) {
        groupRepository.delete(group);
    }

    public List<Appuser> getPossibleMatches(Appgroup group) {
        return null;
    }

    public List<Appuser> getMatches(Appgroup group) {
        return null;
    }

}
