package ch.LFG.service;

import ch.LFG.entity.GroupContact;
import ch.LFG.repository.GroupContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupContactService {

    @Autowired
    private GroupContactRepository groupContactRepository;

    public List<GroupContact> getAll(){
        return groupContactRepository.findAll();
    }

    public GroupContact getContact(long userId) {
        return groupContactRepository.getOne(userId);
    }

    public void setContact(long userId) {

    }

    public void updateContact(long userId) {

    }
}
