package ch.LFG.controller;

import ch.LFG.entity.Appgroup;
import ch.LFG.entity.Appuser;
import ch.LFG.entity.Match;
import ch.LFG.entity.MatchAnswer;
import ch.LFG.service.GroupService;
import io.swagger.v3.oas.annotations.Operation;
import net.minidev.json.JSONObject;
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
    public Appgroup getGroupProfile(@PathVariable long id){
        return groupService.getGroupProfile(id);
    }

    @Operation(summary = "Create new group", description = "Create new group")
    @RequestMapping( method = RequestMethod.POST)
    public Appgroup setGroupProfile(@RequestBody Appgroup group) {
        // TODO Issue 90: ID should get set from frontend (loginid)
        group.setGroupId(1337);
        return groupService.setGroupProfile(group);
    }

    @Operation(summary = "Change group", description = "Change group")
    @RequestMapping( value="/update", method = RequestMethod.PATCH)
    public Appgroup updateGroupProfile(@RequestBody Appgroup group) {

        return groupService.updateGroupProfile(group);
    }

    @Operation(summary = "Delete group", description = "Delete group")
    @RequestMapping( value="/{id}", method = RequestMethod.DELETE)
    public Boolean deleteGroupProfile(@PathVariable long id) {
        return groupService.deleteGroupProfile(id);
    }

    @Operation(summary = "Get Match suggestions for group", description = "Get Match Suggestions of a group by his group")
    @RequestMapping(value="/Suggestions/{id}", method = RequestMethod.GET)
    public List<Appuser> getMatchSuggestions(@PathVariable long id) {
        return groupService.getMatchSuggestions(id);
    }

    @Operation(summary = "Get Matches of group", description = "Get the Matches of a group by his groupId, where group and Group swiped yes")
    @RequestMapping(value="/Matches/{id}", method = RequestMethod.GET)
    public List<Appuser> getMyCurrentMatches(@PathVariable long id) {
        return groupService.getMyCurrentMatches(id);
    }

    @Operation(summary = "Match Answer", description = "Give the answer for a proposed match")
    @RequestMapping(value="/MatchesAnswer", method = RequestMethod.POST)
    public Boolean matchAnswer(@RequestBody MatchAnswer match ) {
        try {
            return groupService.setMatchAnswer(match);
        }
        catch(Exception e) {
            return false;
        }
    }

}
