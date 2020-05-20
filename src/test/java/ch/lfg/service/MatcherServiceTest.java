package ch.lfg.service;

import ch.lfg.LfgApplication;
import ch.lfg.entity.Appgroup;
import ch.lfg.entity.Appuser;
import ch.lfg.entity.Match;
import ch.lfg.entity.MatchResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = LfgApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql({"classpath:schema.sql", "classpath:data.sql"})
class MatcherServiceTest {

  @LocalServerPort
  private int port;

  @Autowired
  private UserService userService;
  @Autowired
  private GroupService groupService;

  @Autowired
  private MatcherService matcherService;


  @Test
  void initializeAllMatchSuggestionNull() {
    assertThrows(NullPointerException.class, () -> {
      matcherService.initializeAllMatchSuggestion(null);
    });
  }
  @Test
  void initializeAllMatchSuggestionIllegalArgument() {
    assertThrows(NullPointerException.class, () -> {
      matcherService.initializeAllMatchSuggestion(new Match());
    });
  }

  @Test
  void calculateSuggestionsForUser() {
    Appuser testuser = userService.getUserProfile(1);
    List<MatchResponse> result = matcherService.calculateSuggestions(testuser);

    assertEquals(4, result.size());
    assertEquals(25, (int)result.get(0).getPercentage());
  }

  @Test
  void calculateSuggestionsForGroup() {
    Appgroup testgroup = groupService.getGroupProfile(3);
    List<MatchResponse> result = matcherService.calculateSuggestions(testgroup);

    assertEquals(2, result.size());
    assertEquals(33.33333333333333, result.get(0).getPercentage());
  }

  @Test
  void calculateSuggestionsNull() {
    assertThrows(NullPointerException.class, () -> {
      matcherService.calculateSuggestions(null);
    });
  }
  @Test
  void calculateSuggestionsIllegalArgument() {
    assertThrows(NullPointerException.class, () -> {
      matcherService.calculateSuggestions(new Match());
    });
  }

  @Test
  void getMyCurrentMatchesForUserWithMatches() {
    Appuser user = userService.getUserProfile(2);
    List<Appgroup> result = matcherService.getMyCurrentMatchesForUser(user);

    assertEquals(2, result.size());
    assertEquals("Rennen", result.get(0).getName());
  }

  @Test
  void getMyCurrentMatchesForUserNoMatches() {
    Appuser user = userService.getUserProfile(1);
    List<Appgroup> result = matcherService.getMyCurrentMatchesForUser(user);

    assertEquals(0, result.size());
  }
  @Test
  void getMyCurrentMatchesForUserInvalidId() {
    Appuser user = new Appuser(99,99,"","","","","",true,0,"",null);
    assertEquals(new ArrayList<>(), matcherService.getMyCurrentMatchesForUser(user));
  }

  @Test
  void getMyCurrentMatchesForGroupWithMatches() {
    Appgroup group = groupService.getGroupProfile(2);
    List<Appuser> result = matcherService.getMyCurrentMatchesForGroup(group);
    assertEquals(1, result.size());
    assertEquals("Fauser", result.get(0).getLastName());
    assertEquals("Habian", result.get(0).getFirstName());
  }

  @Test
  void getMyCurrentMatchesForGroupNoMatches() {
    Appgroup group = groupService.getGroupProfile(1);
    List<Appuser> result = matcherService.getMyCurrentMatchesForGroup(group);

    assertEquals(0, result.size());
  }

  @Test
  void getMyCurrentMatchesForGroupInvalidId() {
    Appgroup group = new Appgroup(99,99,"","","","",true, "", null);
    assertEquals(new ArrayList<>(), matcherService.getMyCurrentMatchesForGroup(group));
  }

  @Test
  void setMatchAnswerIllegalArgument() {
    assertThrows(InvalidParameterException.class, () -> {
      matcherService.setMatchAnswer(1L, 1L, true, 5);
    });
  }

}