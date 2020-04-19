package ch.LFG.controller;

import ch.LFG.entity.Appuser;
import ch.LFG.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Group")
public class GroupController {
    @Autowired
    private GroupService groupService;
    @Autowired
    private SessionService sessionService;
    
    //further commands see https://github.com/swagger-api/swagger-core/wiki/Swagger-2.X---Annotations#OpenAPIDefinition
    @Operation(summary = "Get Groups", description = "Get list of all groups")
    @GetMapping
    public List<Appgroup> findAll() {
        return groupService.getAll();
    }

    @Operation(summary = "Get Group by id", description = "Get one Group by ID")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Appgroup getGroupProfile(@PathVariable int id){
        return groupService.getGroupProfil(id);
    }

    @Operation(summary = "Create new Group", description = "Create new Group")
    @RequestMapping(value="/set", method = RequestMethod.PUT)
    public void setGroupProfil(@RequestBody Appgroup group) {
        groupService.setUserProfil(group);
    }

    @Operation(summary = "Update Group", description = "Update existing Group")
    @RequestMapping(value="/update", method = RequestMethod.PATCH)
    public Appgroup updateGroupProfil(@RequestBody Appgroup group) {
        Appgroup updatedGroup = groupService.updateUserProfile(group, group.getGroupId());
        return updatedGroup;
    }

    @Operation(summary = "Update Group", description = "Update existing Group")
    @RequestMapping(value="/delete", method = RequestMethod.DELETE)
    public Appgroup deleteGroupProfil(@RequestBody Appgroup group) {
        Appgroup updatedUser = groupService.updateGroupProfile(group, group.getGroupId());
        return updatedUser;
    }


    @Operation(summary = "Get possible Matches for Group", description = "Get all the possible Matches of a Group by his groupId")
    @RequestMapping(value="/PossibleMatches/{id}", method = RequestMethod.GET)
    public List<Appuser> getPossibleMatches(@PathVariable Long id, @RequestBody Appgroup group) {
        return userService.getPossibleMatches(usgrouper);
    }

    @Operation(summary = "Get Matches of Group", description = "Get all the Matches of a Group by his groupId")
    @RequestMapping(value="/Matches/{id}", method = RequestMethod.GET)
    public List<Appuser> getMatches(@PathVariable Long id, @RequestBody Appgroup group) {
        return userService.getMatches(group);
    }
}
