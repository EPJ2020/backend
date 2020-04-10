package ch.LFG.controller;

import ch.LFG.entity.Group;
import ch.LFG.entity.Login;
import ch.LFG.entity.User;
import ch.LFG.loginService.SessionService;
import ch.LFG.entityService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/User")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private SessionService sessionService;

    @PostMapping
    @RequestMapping(value="/Login/{id}", method = RequestMethod.PUT)
    public void registerUser(@PathVariable Long id, @RequestBody Login login) {
        sessionService.registerUser(login);
    }

    @PostMapping
    @RequestMapping(value="/User/{id}", method = RequestMethod.PUT)
    public void setUserProfil(@PathVariable Long id, @RequestBody User user) {
        userService.updateUserProfil(user);
    }

    @PostMapping
    @RequestMapping(value="/User/{id}", method = RequestMethod.PATCH)
    public void updateUserProfil(@PathVariable Long id, @RequestBody User user) {
        userService.updateUserProfil(user);
    }

    @GetMapping
    @RequestMapping(value="/User/{id}", method = RequestMethod.GET)
    public User getUserProfil(@PathVariable Long id, @RequestBody User user) {
        return userService.getUserProfil(user);
    }

    @GetMapping
    @RequestMapping(value="/User/Group/{id}", method = RequestMethod.GET)
//    @ResponseBody
    public List<Group> getAllGroups(@PathVariable Long id, @RequestBody User user) {
        return userService.getAllGroups(user);
    }

    @GetMapping
    @RequestMapping(value="/User/PossibleMatches/{id}", method = RequestMethod.GET)
    public List<Group> getPossibleMatches(@PathVariable Long id, @RequestBody User user) {
        return userService.getPossibleMatches(user);
    }

    @GetMapping
    @RequestMapping(value="/User/Matches/{id}", method = RequestMethod.GET)
    public List<Group> getMatches(@PathVariable Long id, @RequestBody User user) {
        return userService.getMatches(user);
    }


    // Bsp Methoden
//    @RequestMapping(value="{id}", method = RequestMethod.PUT) // PUT for overwriting all existing attributes, PATCH for only overwriting a few
//    public Session update(@PathVariable Long id, @RequestBody Session session) {
//        // check if all attributes are there to initiate a Session - needs validation otherwise return 400 bad payload
//        Session existingSession = sessionRepository.getOne(id);
//        copyProperties(session, existingSession, "session_id");
//        return sessionRepository.saveAndFlush(existingSession);
//    }
}
