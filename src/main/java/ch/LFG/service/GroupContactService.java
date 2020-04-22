package ch.LFG.service;

import ch.LFG.entity.GroupContact;
import ch.LFG.repository.GroupContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.CompletableFuture;

public class GroupContactService {

    @Autowired
    private GroupContactRepository groupContactRepository;

    @Async
    public CompletableFuture<GroupContact> getContact(long groupId) {
        return CompletableFuture.completedFuture(groupContactRepository.getOne(groupId));
    }

    @Async
    public CompletableFuture<GroupContact> setContact(GroupContact contact) {
        groupContactRepository.save(contact);
        return CompletableFuture.completedFuture(groupContactRepository.getOne(contact.getGroupContactId()));
    }

    @Async
    public CompletableFuture<GroupContact> updateContact(GroupContact contact) {
        groupContactRepository.save(contact);
        return CompletableFuture.completedFuture(groupContactRepository.getOne(contact.getGroupContactId()));
    }
}
