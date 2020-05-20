package ch.lfg.controller;

import ch.lfg.LfgApplication;
import ch.lfg.entity.Appgroup;
import ch.lfg.entity.Appuser;
import ch.lfg.entity.MatchAnswer;
import ch.lfg.entity.MatchResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.jdbc.Sql;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = LfgApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql({"classpath:schema.sql", "classpath:data.sql"})
class GroupControllerTest {

  @LocalServerPort
  private int port;

  @Autowired
  private UserController userController;
  @Autowired
  private GroupController groupController;

  private String[] tags = {"extreme", "alchemy", "lets blow all our problems up"};
  private Appgroup group = new Appgroup(1, 1, "Alchemists united", "alchemy@amestris", "777", "we rule the world", true, "amestris", tags);

  private void setSecurityContext(String userinfo) {
    var userPrincipal = new User(userinfo, userinfo, new ArrayList<>());
    var auth = new TestingAuthenticationToken(userPrincipal, null, new ArrayList<>());
    auth.setAuthenticated(true);
    SecurityContextHolder.getContext().setAuthentication(auth);
  }

  @Test
  void getGroupProfile() throws IllegalAccessException {
    setSecurityContext("Test1");
    assertEquals("Feierabend Bier", groupController.getGroupProfile(1).getName());
  }
  @Test
  void getGroupProfileInvalidId() {
    assertThrows(JpaObjectRetrievalFailureException.class, () -> {
      groupController.getGroupProfile(17);
    });
  }
  @Test
  void getGroupProfileGroupNotFromUser() {
    setSecurityContext("Test1");
    assertThrows(IllegalAccessException.class, () -> {
      groupController.getGroupProfile(3);
    });
  }

  @Test
  void setGroupProfile() throws IllegalAccessException {
    setSecurityContext("Test1");
    assertEquals(group.getName(), groupController.setGroupProfile(group).getName());
  }

  @Test
  void setGroupProfileExistingId() throws IllegalAccessException {
    setSecurityContext("Test1");
    group.setGroupId(1);
    assertEquals(group.getName(), groupController.setGroupProfile(group).getName());
    assertNotEquals(group.getGroupId(), groupController.setGroupProfile(group).getGroupId());
  }

  @Test
  void setGroupProfileNull() {
    assertThrows(NullPointerException.class, () -> {
      groupController.setGroupProfile(null);
    });
  }

  @Test
  void updateGroupProfile() throws IllegalAccessException {
    setSecurityContext("Test1");
    group.setGroupId(1);
    assertEquals(group.getName(), groupController.updateGroupProfile(group).getName());
  }

  @Test
  void updateGroupProfileGroupNotFromUser() {
    setSecurityContext("Test2");
    group.setGroupId(1);
    assertThrows(IllegalAccessException.class, () -> {
      groupController.updateGroupProfile(group);
    });
  }

  @Test
  void updateGroupProfileInvalidId() {
    group.setGroupId(101);
    assertThrows(IllegalAccessException.class, () -> {
      groupController.updateGroupProfile(group);
    });
  }

  @Test
  void updateUserProfileNull() {
    assertThrows(NullPointerException.class, () -> {
      groupController.updateGroupProfile(null);
    });
  }

  @Test
  void deleteGroupProfile() throws IllegalAccessException {
    setSecurityContext("Test3");
    assertEquals(true, groupController.deleteGroupProfile(5));
  }

  @Test
  void deleteGroupProfileGroupNotFromUser() throws IllegalAccessException {
    setSecurityContext("Test3");
    assertEquals(false, groupController.deleteGroupProfile(1));
  }

  @Test
  void deleteGroupProfileInvalidId() throws IllegalAccessException {
    setSecurityContext("Test1");
    assertEquals(false, groupController.deleteGroupProfile(100));
  }

  @Test
  void getMatchSuggestions() throws IllegalAccessException {
    setSecurityContext("Test4");
    List<MatchResponse> result = groupController.getMatchSuggestions(8);
    assertEquals(1, result.size()); //Would be 7 and 8, but 8 is not active
    assertEquals("ras Gupta", ((Appuser)result.get(0).getObject()).getLastName());
  }

  @Test
  void getMatchSuggestionsGroupNotFromUser() throws IllegalAccessException {
    setSecurityContext("Test1");
    assertThrows(IllegalAccessException.class, () -> {
      groupController.getMatchSuggestions(8);
    });
  }

  @Test
  void getMatchSuggestionsGroupHasNone() throws IllegalAccessException {
    setSecurityContext("Test4");

    List<MatchResponse> result = groupController.getMatchSuggestions(2);
    assertEquals(0, result.size());
  }

  @Test
  void getMyCurrentMatches() throws IllegalAccessException {
    setSecurityContext("Test2");
    Appuser user2 = userController.getUserProfile();//2
    setSecurityContext("Test4");

    List<Appuser> result = groupController.getMyCurrentMatches(2);

    assertEquals(1, result.size());
    assertEquals(user2.getUserId(), result.get(0).getUserId());
  }

  @Test
  void getMyCurrentMatchesGroupNotFromUser() throws IllegalAccessException {
    setSecurityContext("Test1");
    assertThrows(IllegalAccessException.class, () -> {
      groupController.getMyCurrentMatches(2);
    });
  }

  @Test
  void getMyCurrentMatchesNoMatches() throws IllegalAccessException {
    setSecurityContext("Test4");
    List<Appuser> result = groupController.getMyCurrentMatches(8);
    assertEquals(0, result.size());
  }

  @Test
  void matchAnswer() throws IllegalAccessException {
    setSecurityContext("Test4");
    List<Appuser> result = new ArrayList<>();
    assertEquals(result, groupController.getMyCurrentMatches(8));

    MatchAnswer answer = new MatchAnswer();
    answer.setUserId(1); // User 1 has true in userAccept for Group 8
    answer.setGroupId(8);
    answer.setAnswer(true);

    boolean answerSet = groupController.matchAnswer(answer);
    result = groupController.getMyCurrentMatches(8);

    assertEquals(true, answerSet);
    assertEquals(1, result.size());
    assertEquals("Deller", result.get(0).getLastName());
  }

  @Test
  void matchAnswerNull() throws IllegalAccessException {
    MatchAnswer answer = null;
    assertEquals(false, groupController.matchAnswer(answer));
  }

  @Test
  void matchAnswerGroupFromUser() throws IllegalAccessException {
    MatchAnswer answer = new MatchAnswer();
    answer.setUserId(1);
    answer.setGroupId(1); // Group 1 belongs to User 1
    answer.setAnswer(true);

    assertEquals(false, groupController.matchAnswer(answer));
  }
}