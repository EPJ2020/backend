package ch.LFG.service;

import ch.LFG.entity.Appgroup;
import ch.LFG.entity.Appuser;

import ch.LFG.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private MatcherService matchService;
    private GroupService groupService;

    //nicht in modell
    @Async
    public CompletableFuture<List<Appuser>> getAll(){
        return CompletableFuture.completedFuture(userRepository.findAll());
    }

    @Async
    public CompletableFuture<Appuser> getUserProfil(long id){ return CompletableFuture.completedFuture(userRepository.getOne(id));}

    @Async
    public void setUserProfil(Appuser user){ userRepository.save(user);}

    public Appuser updateUserProfile(Appuser user, long id){ userRepository.save(user); return userRepository.getOne(id);}

    @Async
    public CompletableFuture<List<Appgroup>> getAllGroups(Appuser user) {
        return null; //CompletableFuture.completedFuture(groupService.getPossibleMatches());
    }

    @Async
    public CompletableFuture<List<Appgroup>> getPossibleMatches(Appuser user) {
        return null;
    }

    @Async
    public CompletableFuture<List<Appgroup>> getMatches(Appuser user) {
        return null;
    }

}

