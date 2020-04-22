package ch.LFG.service;

import ch.LFG.entity.Appgroup;
import ch.LFG.entity.Appuser;
import ch.LFG.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    private MatcherService matcherService;

    public Appgroup getGroupProfile(long groupId) {
        return groupRepository.getOne(groupId);
    }

    public Appgroup setGroupProfile(Appgroup group) {
        groupRepository.save(group);
        return groupRepository.getOne(group.getGroupId());
    }

    public Appgroup updateGroupProfile(Appgroup group) {
        groupRepository.save(group);
        return groupRepository.getOne(group.getGroupId());
    }

    public Boolean deleteGroupProfile(long groupId) {

        try {
            groupRepository.delete(groupRepository.getOne(groupId));
            return true;
        }
        catch(Exception e) {
            return false;
        }

    }

    public List<Appuser> getMatchSuggestions(long groupId) {
        return null;
    }

    public List<Appuser> getMyCurrentMatches(long groupId) {
        return null;
    }

    public Boolean setMatchAnswer(long groupId, long userId, Boolean answer){
        return null;
    }

}
