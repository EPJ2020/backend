package ch.LFG.controller;

import ch.LFG.entity.Group;
import ch.LFG.entity.Login;
import ch.LFG.entity.User;
import ch.LFG.service.SessionService;
import ch.LFG.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.beans.BeanUtils.copyProperties;


@RestController
@RequestMapping("/User")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private SessionService sessionService;


    @PostMapping
    public void registerUser(Login user) {
    }
    @PostMapping
    public void updateUser(Login user) {
    }
    @PostMapping
    public void setUserProfil(User user) {
    }
    @PostMapping
    public void updateUserProfil(User user) {
    }

    @GetMapping
    @RequestMapping("{id}")
    public List<Group> getUserProfil(@PathVariable Long userId) {
        return userService.getAllGroups(userId);
    }

    @GetMapping
    public List<User> getAllGroups(long userId) {
        return null;
    }

    @GetMapping
    public List<User> getPossibleMatches(long userId) {
        return null;
    }

    @GetMapping
    public List<User> getMatches(long userId) {
        return null;
    }


    // Bsp Methoden

//    @RequestMapping(value="{id}", method = RequestMethod.DELETE) //requires the HTTP word delete with an id and this endpoint
//    public void delete(@PathVariable Long id){
//        //need to check for childrecords before deleting -- cascade, db doesnt allow if there are children
//        sessionRepository.deleteById(id);
//    }
//
//    @RequestMapping(value="{id}", method = RequestMethod.PUT) // PUT for overwriting all existing attributes, PATCH for only overwriting a few
//    public Session update (@PathVariable Long id, @RequestBody Session session) {
//        // check if all attributes are there to initiate a Session - needs validation otherwise return 400 bad payload
//        Session existingSession = sessionRepository.getOne(id);
//        copyProperties(session, existingSession, "session_id");
//        return sessionRepository.saveAndFlush(existingSession);
//    }
}
