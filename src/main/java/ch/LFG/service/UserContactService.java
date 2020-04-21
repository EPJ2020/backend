package ch.LFG.service;

import ch.LFG.entity.UserContact;
import ch.LFG.repository.OurRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserContactService {
    @Autowired
    private OurRepository<UserContact> userContactRepository;

    public void setContact(UserContact contact) {
        userContactRepository.save(contact);

    }

    public UserContact updateContact(UserContact contact, long id) {
        userContactRepository.save(contact);
        return userContactRepository.getOne(id);
    }

}
