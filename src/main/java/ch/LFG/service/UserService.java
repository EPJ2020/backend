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

    @Async
    public CompletableFuture<Appuser> getUserProfile(long id){
        return CompletableFuture.completedFuture(userRepository.getOne(id));
    }

    @Async
    public CompletableFuture<Appuser> setUserProfile(Appuser user){
        userRepository.save(user);
        return CompletableFuture.completedFuture(userRepository.getOne(user.getUserId()));
    }

    @Async
    public CompletableFuture<Appuser> updateUserProfile(Appuser user, long id){
        userRepository.save(user);
        return CompletableFuture.completedFuture(userRepository.getOne(id));
    }

    @Async
    public CompletableFuture<List<Appgroup>> getMyGroups(long userId) {
        return null;
    }

    @Async
    public CompletableFuture<List<Appgroup>> getMatchSuggestion(long userid) {
        return null;
    }

    @Async
    public CompletableFuture<List<Appgroup>> getMyCurrentMatches(long userid) {
        return null;
    }

    @Async
    public void setMatchAnswer(long groupId, long userId, Boolean answer){

    }

}

