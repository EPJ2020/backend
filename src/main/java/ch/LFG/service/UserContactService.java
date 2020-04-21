package ch.LFG.service;

import ch.LFG.entity.UserContact;
import ch.LFG.repository.GroupRepository;
import ch.LFG.repository.UserContactRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserContactService {
    @Autowired
    private UserContactRepository userContactRepository;

    public void setContact(UserContact contact) {
        userContactRepository.save(contact);

    }

    public UserContact updateContact(UserContact contact, long id) {
        userContactRepository.save(contact);
        return userContactRepository.getOne(id);
    }

}
