package ch.LFG.controller;

import ch.LFG.entity.Appgroup;
import ch.LFG.entity.Appuser;
import ch.LFG.service.GroupService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/Group")
public class GroupController {

    @Autowired
    private GroupService groupService;


    //further commands see https://github.com/swagger-api/swagger-core/wiki/Swagger-2.X---Annotations#OpenAPIDefinition
    @Operation(summary = "Get group by id", description = "Get one group by his ID")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CompletableFuture<Appgroup> getGroupProfile(@PathVariable long groupId){
        //return groupService.getGroupProfile(groupId);
        return null;
    }

    @Operation(summary = "Create new group", description = "Create new group")
    @RequestMapping( method = RequestMethod.POST)
    public CompletableFuture<Appgroup> setGroupProfile(@RequestBody Appgroup group) {
        //return groupService.setGroupProfile(group);
        return null;
    }

    @Operation(summary = "Change group", description = "Change group")
    @RequestMapping( method = RequestMethod.PATCH)
    public CompletableFuture<Appgroup> updateGroupProfile(@RequestBody Appgroup group) {
        //groupService.updateGroupProfile(group);
        return null;
    }

    @Operation(summary = "Delete group", description = "Delete group")
    @RequestMapping( value="/{id}", method = RequestMethod.DELETE)
    public CompletableFuture<Boolean> deleteGroupProfile(@PathVariable Long groupId) {
        //groupService.updateGroupProfile(group);
        return null;
    }


    @Operation(summary = "Get Groups from group by groupId", description = "Get all the groups of a group by his groupId")
    @RequestMapping(value="/MyGroups/{id}", method = RequestMethod.GET)
    public CompletableFuture<List<Appuser>> getMyGroups(@PathVariable Long groupId) {
        //return groupService.getMyGroups(groupId);
        return null;
    }

    @Operation(summary = "Get Match suggestions for group", description = "Get Match Suggestions of a group by his group")
    @RequestMapping(value="/Suggestions/{id}", method = RequestMethod.GET)
    public CompletableFuture<List<Appuser>> getMatchSuggestions(@PathVariable Long groupId) {
        //return groupService.getMatchSuggestions(groupId);
        return null;
    }

    @Operation(summary = "Get Matches of group", description = "Get the Matches of a group by his groupId, where group and Group swiped yes")
    @RequestMapping(value="/Matches/{id}", method = RequestMethod.GET)
    public CompletableFuture<List<Appuser>> getMyCurrentMatches(@PathVariable Long groupId) {
        //return groupService.getMyCurrentMatches(groupId);
        return null;
    }

    @Operation(summary = "Match Answer", description = "Give the answer for a proposed match")
    @RequestMapping(value="/MatchesAnswer/{id}", method = RequestMethod.POST)
    public CompletableFuture<List<Appuser>> matchAnswer(@RequestBody Long userId, Long groupId, Boolean answer ) {
        //return groupService.matchAnswer(userId, groupId, answer);
        return null;
    }

}
