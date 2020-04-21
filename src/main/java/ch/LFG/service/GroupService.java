package ch.LFG.service;

import ch.LFG.entity.Appgroup;
import ch.LFG.entity.Appuser;
import ch.LFG.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    private MatcherService matchService;
    private UserContactService userContactService;

    @Autowired
    private GroupRepository groupRepository;

    public Appgroup getGroupProfil(Appgroup group) {
        return null; //groupRepository.findById(groupRepository.getOne(group));
    }

    public void setGroupProfil(Appgroup group) {
        groupRepository.save(group);
    }

    public Appgroup updateGroupProfil(Appgroup group, long id) {
        groupRepository.save(group);
        return groupRepository.getOne(id);
    }

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
