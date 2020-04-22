package ch.LFG.controller;

import ch.LFG.entity.Appgroup;
import ch.LFG.entity.Appuser;
import ch.LFG.entity.Userlogin;
import ch.LFG.service.LoginService;
import ch.LFG.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/User")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private LoginService loginService;

    //further commands see https://github.com/swagger-api/swagger-core/wiki/Swagger-2.X---Annotations#OpenAPIDefinition
    @Operation(summary = "Get User by id", description = "Get one user by his ID")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Appuser getUserProfile(@PathVariable long id){
        return userService.getUserProfile(id);
    }

    @Operation(summary = "Create new User", description = "Create new User")
    @RequestMapping( method = RequestMethod.POST)
    public Appuser setUserProfile(@RequestBody Appuser user) {
        return userService.setUserProfile(user);
    }

    @Operation(summary = "Change User", description = "Change User")
    @RequestMapping( method = RequestMethod.PATCH)
    public Appuser updateUserProfile(@RequestBody Appuser user) {
        return userService.updateUserProfile(user);
    }

    @Operation(summary = "Get Groups from User by userId", description = "Get all the groups of a user by his userId")
    @RequestMapping(value="/MyGroups/{id}", method = RequestMethod.GET)
    public List<Appgroup> getMyGroups(@PathVariable Long id) {
        return userService.getMyGroups(id);
    }

    @Operation(summary = "Get Match suggestions for User", description = "Get Match Suggestions of a User by his userId")
    @RequestMapping(value="/Suggestions/{id}", method = RequestMethod.GET)
    public List<Appgroup> getMatchSuggestions(@PathVariable Long userId) {
        //return userService.getMatchSuggestions(userId);
        return null;
    }

    @Operation(summary = "Get Matches of User", description = "Get the Matches of a User by his userId, where User and Group swiped yes")
    @RequestMapping(value="/Matches/{id}", method = RequestMethod.GET)
    public List<Appgroup> getMyCurrentMatches(@PathVariable Long userId) {
        //return userService.getMyCurrentMatches(userId);
        return null;
    }

    @Operation(summary = "Match Answer", description = "Give the answer for a proposed match")
    @RequestMapping(value="/MatchesAnswer/{id}", method = RequestMethod.POST)
    public List<Appgroup> matchAnswer(@RequestBody Long userId, Long groupId, Boolean answer ) {
        //return userService.matchAnswer(userId, groupId, answer);
        return null;
    }

    @Operation(summary = "Create new Login", description = "Create new Login")
    @RequestMapping( value = "/Register", method = RequestMethod.POST)
    public void registerUser(@RequestBody Userlogin login) {
        loginService.createUserLogin(login);
    }
}
