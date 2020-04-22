package ch.LFG.controller;

import ch.LFG.entity.Appgroup;
import ch.LFG.entity.Appuser;
import ch.LFG.service.GroupService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    //further commands see https://github.com/swagger-api/swagger-core/wiki/Swagger-2.X---Annotations#OpenAPIDefinition
    @Operation(summary = "Get group by id", description = "Get one group by his ID")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Appgroup getGroupProfile(@PathVariable long groupId){
        return groupService.getGroupProfile(groupId);
    }

    @Operation(summary = "Create new group", description = "Create new group")
    @RequestMapping( method = RequestMethod.POST)
    public Appgroup setGroupProfile(@RequestBody Appgroup group) {
        return groupService.setGroupProfile(group);
    }

    @Operation(summary = "Change group", description = "Change group")
    @RequestMapping( method = RequestMethod.PATCH)
    public Appgroup updateGroupProfile(@RequestBody Appgroup group) {
        return groupService.updateGroupProfile(group);
    }

    @Operation(summary = "Delete group", description = "Delete group")
    @RequestMapping( value="/{id}", method = RequestMethod.DELETE)
    public Boolean deleteGroupProfile(@PathVariable Long groupId) {
        return groupService.deleteGroupProfile(groupId);
    }

    @Operation(summary = "Get Match suggestions for group", description = "Get Match Suggestions of a group by his group")
    @RequestMapping(value="/Suggestions/{id}", method = RequestMethod.GET)
    public List<Appuser> getMatchSuggestions(@PathVariable Long groupId) {
        return groupService.getMatchSuggestions(groupId);
    }

    @Operation(summary = "Get Matches of group", description = "Get the Matches of a group by his groupId, where group and Group swiped yes")
    @RequestMapping(value="/Matches/{id}", method = RequestMethod.GET)
    public List<Appuser> getMyCurrentMatches(@PathVariable Long groupId) {
        return groupService.getMyCurrentMatches(groupId);
    }

    @Operation(summary = "Match Answer", description = "Give the answer for a proposed match")
    @RequestMapping(value="/MatchesAnswer/{id}", method = RequestMethod.POST)
    public Boolean matchAnswer(@RequestBody Long userId, Long groupId, Boolean answer ) {
        return groupService.setMatchAnswer(userId, groupId, answer);
    }

}
