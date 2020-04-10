package ch.LFG.entityService;

import ch.LFG.entity.GroupContact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupContactService {

    @Autowired
    private JpaRepository<GroupContact, Long> groupContactRepository;

    public List<GroupContact> getAll(){
        return groupContactRepository.findAll();
    }

    public GroupContact getContact(GroupContact contact) {
        return null;//groupContactRepository.getOne(contact.getGroup());
    }

    public void setContact(GroupContact contact) {

    }

    public void updateContact(GroupContact contact) {

    }
}
