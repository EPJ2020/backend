package ch.LFG.service;

import ch.LFG.entity.Appgroup;
import ch.LFG.entity.GroupContact;
import ch.LFG.repository.GroupContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public class GroupContactService {

    @Autowired
    private GroupContactRepository groupContactRepository;

    public GroupContact getContact(Appgroup group) {
        return  null; //groupContactRepository.findOne(group.getGroupId());
    }

    public void setContact(GroupContact contact) {
        groupContactRepository.save(contact);
    }

    public GroupContact updateContact(GroupContact contact, long id) {
        groupContactRepository.save(contact);
        return groupContactRepository.getOne(id);
    }
}
