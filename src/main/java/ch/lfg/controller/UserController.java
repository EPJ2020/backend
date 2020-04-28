package ch.lfg.controller;

import ch.lfg.entity.Appgroup;
import ch.lfg.entity.Appuser;
import ch.lfg.entity.MatchAnswer;
import ch.lfg.entity.Userlogin;
import ch.lfg.service.LoginService;
import ch.lfg.service.UserService;
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
        // TODO Issue 90: ID should get set from frontend (loginid)
        user.setUserId(1337);
        return userService.setUserProfile(user);
    }

    @Operation(summary = "Change User", description = "Change User")
    @RequestMapping( value="/update", method = RequestMethod.PATCH)
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
    public List<Appgroup> getMatchSuggestions(@PathVariable Long id) {
        return userService.getMatchSuggestion(id);
    }

    @Operation(summary = "Get Matches of User", description = "Get the Matches of a User by his userId, where User and Group swiped yes")
    @RequestMapping(value="/Matches/{id}", method = RequestMethod.GET)
    public List<Appgroup> getMyCurrentMatches(@PathVariable long id) {
        return userService.getMyCurrentMatches(id);
    }

    @Operation(summary = "Match Answer", description = "Give the answer for a proposed match")
    @RequestMapping(value="/MatchesAnswer/", method = RequestMethod.POST)
    public Boolean matchAnswer(@RequestBody MatchAnswer match) {
        try {
            userService.setMatchAnswer(match);
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    @Operation(summary = "Create new Login", description = "Create new Login")
    @RequestMapping( value = "/Register", method = RequestMethod.POST)
    public void registerUser(@RequestBody Userlogin login) {
        loginService.createUserLogin(login);
    }
}
