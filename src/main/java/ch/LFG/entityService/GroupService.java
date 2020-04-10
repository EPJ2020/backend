package ch.LFG.entityService;

import ch.LFG.entity.Group;
import ch.LFG.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public class GroupService {

    private MatchService matchService;
    private UserContactService userContactService;

    @Autowired
    private JpaRepository<User, Long> groupRepository;

    public Group getGroupProfil(Group group) {
        return null;
    }

    public void setGroupProfil() {}

    public Group updateGroupProfil(Group group) {
        return null;
    }

    public void deleteGroupProfil(Group group) {}

    public List<User> getPossibleMatches(Group group) {
        return null;
    }

    public List<User> getMatches(Group group) {
        return null;
    }

}
