package ch.lfg.controller;

import ch.lfg.entity.Appgroup;
import ch.lfg.entity.Appuser;
import ch.lfg.entity.MatchAnswer;
import ch.lfg.entity.MatchResponse;
import ch.lfg.service.GroupService;
import ch.lfg.authorization.AuthorizationService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * See http://152.96.56.38:8080/swagger-ui.html for documentation
 */
@RestController
@RequestMapping("/Group")
public class GroupController {

  @Autowired
  private AuthorizationService authorizationService;

  @Autowired
  private GroupService groupService;

  //further commands see https://github.com/swagger-api/swagger-core/wiki/Swagger-2.X---Annotations#OpenAPIDefinition
  @Operation(summary = "Get group by id", description = "Get one group by his ID")
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public Appgroup getGroupProfile(@PathVariable long id) throws IllegalAccessException {
    if (authenticateUserFromGroup(id)) {
      return groupService.getGroupProfile(id);
    } else {
      throw new IllegalAccessException("Access Denied");
    }

  }

  @Operation(summary = "Create new group", description = "Create new group")
  @RequestMapping(method = RequestMethod.POST)
  public Appgroup setGroupProfile(@RequestBody Appgroup group) {
    group.setOwnerId(authorizationService.getUserByLoginId().getUserId());
    group.setGroupId(9999);
    return groupService.setGroupProfile(group);
  }

  @Operation(summary = "Change group", description = "Change group")
  @RequestMapping(value = "/update", method = RequestMethod.PATCH)
  public Appgroup updateGroupProfile(@RequestBody Appgroup group) throws IllegalAccessException {
    if (group.getOwnerId() == authorizationService.getUserByLoginId().getUserId()) {
      return groupService.updateGroupProfile(group);
    } else {
      throw new IllegalAccessException("Access Denied");
    }
  }

  @Operation(summary = "Delete group", description = "Delete group")
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public Boolean deleteGroupProfile(@PathVariable long id) {
    try {
      if (authenticateUserFromGroup(id)) {
        return groupService.deleteGroupProfile(id);
      } else {
        return false;
      }
    } catch (Exception e) {
      return false;
    }
  }

  @Operation(summary = "Get Match suggestions for group",
          description = "Get Match Suggestions of a group by his group")
  @RequestMapping(value = "/Suggestions/{id}", method = RequestMethod.GET)
  public List<MatchResponse> getMatchSuggestions(@PathVariable long id)
      throws IllegalAccessException {
    if (authenticateUserFromGroup(id)) {
      return groupService.getMatchSuggestions(id);
    } else {
      throw new IllegalAccessException("Access Denied");
    }
  }

  @Operation(summary = "Get Matches of group",
          description = "Get the Matches of a group by his groupId, where group and Group swiped yes")
  @RequestMapping(value = "/Matches/{id}", method = RequestMethod.GET)
  public List<Appuser> getMyCurrentMatches(@PathVariable long id)
      throws IllegalAccessException {
    if (authenticateUserFromGroup(id)) {
      return groupService.getMyCurrentMatches(id);
    } else {
      throw new IllegalAccessException("Access Denied");
    }
  }

  @Operation(summary = "Match Answer", description = "Give the answer for a proposed match")
  @RequestMapping(value = "/MatchesAnswer", method = RequestMethod.POST)
  public Boolean matchAnswer(@RequestBody MatchAnswer match) throws IllegalAccessException {
    try {
      if (authenticateUserFromGroup(match.getGroupId())
              || authorizationService.getUserFromToken().getLoginId() == match.getUserId()) {
        return groupService.setMatchAnswer(match);
      } else {
        return false;
      }
    } catch (Exception e) {
      return false;
    }
  }

  private boolean authenticateUserFromGroup(long id) {
    long userId = authorizationService.getUserByLoginId().getUserId();
    Appgroup group = groupService.getGroupProfile(id);
    return group.getOwnerId() == userId;
  }

}
