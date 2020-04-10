package ch.LFG.entityService;

import ch.LFG.entity.User;
import ch.LFG.entity.UserContact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public class UserContactService {
    @Autowired
    private JpaRepository<UserContact, Long> userContactRepository;

    public UserContact getContact(User user) {
        return null;
    }

    public void setContact() {

    }

    public UserContact updateContact(User user) {
        return null;
    }
}
