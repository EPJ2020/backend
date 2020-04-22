package ch.LFG.service;

import ch.LFG.entity.UserContact;
import ch.LFG.repository.UserContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.CompletableFuture;

public class UserContactService {
    @Autowired
    private UserContactRepository userContactRepository;

    @Async
    public CompletableFuture<UserContact> getContact(long contactId){
        return CompletableFuture.completedFuture(userContactRepository.getOne(contactId));
    }

    @Async
    public CompletableFuture<UserContact> setContact(UserContact contact) {
        userContactRepository.save(contact);
        return CompletableFuture.completedFuture(userContactRepository.getOne(contact.getUserContactId()));

    }

    @Async
    public CompletableFuture<UserContact> updateContact(UserContact contact) {
        userContactRepository.save(contact);
        return CompletableFuture.completedFuture(userContactRepository.getOne(contact.getUserContactId()));
    }

}
