package ch.lfg.controller;

import ch.lfg.LfgApplication;
import ch.lfg.entity.*;
import ch.lfg.exception.FailedLoginException;
import ch.lfg.exception.RegistrationException;
import ch.lfg.authorization.AuthorizationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.jdbc.Sql;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = LfgApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql({"classpath:schema.sql", "classpath:data.sql"})
@Transactional
class UserControllerTest {

  @LocalServerPort
  private int port;

  @Autowired
  private GroupController groupController;
  @Autowired
  private UserController userController;
  @Autowired
  private AuthorizationService authorizationService;
  @Mock
  SecurityContext mockSecurityContext;

  private String[] tags = {"extreme", "alchemy", "lets blow all our problems up"};
  private Appuser user = new Appuser(22, 22, "Curtis", "Izumi", "alchemy@amestris", "777", "we rule the world", true, 45, "female",  tags);
  private UserDetails login = new User("Test1", "foofoo", new ArrayList<>());
  private Userlogin userlogin = new Userlogin("Test2", "foofoo");

  private void setSecurityContext(String userinfo) {
    var userPrincipal = new User(userinfo, userinfo, new ArrayList<>());
    var auth = new TestingAuthenticationToken(userPrincipal, null, new ArrayList<>());
    auth.setAuthenticated(true);
    SecurityContextHolder.getContext().setAuthentication(auth);
  }


  @Test
  void register() throws RegistrationException {
    userlogin.setUsername("registertest");
    String token = userController.register(userlogin);
    assertNotNull(token);
    assertNotEquals("user already exists", token);
    assertNotEquals("Please set password and username", token);
    assertEquals(userlogin.getUsername(), authorizationService.loadUserByUsername(userlogin.getUsername()).getUsername());
  }

  @Test
  void registerUsernameExists() throws RegistrationException {
    assertThrows(RegistrationException.class, () -> {
      userController.register(userlogin);
    });
  }

  @Test
  void registerNullValues() throws RegistrationException {
    userlogin.setUsername(null);

    assertThrows(RegistrationException.class, () -> {
      userController.register(userlogin);
    });

    userlogin.setPassword(null);
    userlogin.setUsername("blah");

    assertThrows(RegistrationException.class, () -> {
      userController.register(userlogin);
    });
  }

  @Test
  void login() throws Exception {
    String token = userController.login(userlogin);
    assertNotNull(token);
  }

  @Test
  void loginWrongPassword() throws Exception {
    userlogin.setPassword("blah");
    assertThrows(FailedLoginException.class, () -> {
      userController.login(userlogin);
    });
  }

  @Test
  void loginEmptyPassword() throws Exception {
    userlogin.setPassword(null);
    assertThrows(FailedLoginException.class, () -> {
      userController.login(userlogin);
    });
  }

  @Test
  void getUserProfile() {
    setSecurityContext("Test1");

    assertEquals("Deller", userController.getUserProfile().getLastName());
  }

  @Test
  void setUserProfile() {
    setSecurityContext("Test9");
    user.setUserId(40);
    assertEquals(user.getLastName(), userController.setUserProfile(user).getLastName());
  }

  @Test
  void setUserProfileExistingId() {
    setSecurityContext("Test1");
    Appuser result = userController.setUserProfile(user);
    assertNotEquals(1, result.getUserId());
    assertEquals(user.getLastName(), result.getLastName());
  }

  @Test
  void setUserProfileNull() {
    assertThrows(NullPointerException.class, () -> {
      userController.setUserProfile(null);
    });
  }

  @Test
  void updateUserProfile() {
    setSecurityContext("Test1");
    assertEquals(user.getLastName(), userController.updateUserProfile(user).getLastName());
  }

  @Test
  void updateUserProfileNull() {
    setSecurityContext("Test1");
    assertThrows(IllegalArgumentException.class, () -> {
      userController.updateUserProfile(null);
    });
  }

  @Test
  void getMyGroups() {
    setSecurityContext("Test1");
    List<Appgroup> groups = userController.getMyGroups();
    assertEquals(1, groups.size());
    assertEquals("Feierabend Bier", groups.get(0).getName());
  }

  @Test
  void getMyGroupsHasNoGroup() {
    setSecurityContext("Test8");
    List<Appgroup> groups = userController.getMyGroups();
    assertEquals(0, groups.size());
  }

  @Test
  void getUserSuggestions() {
    setSecurityContext("Test8");
    List<MatchResponse> result = userController.getUserSuggestions();
    assertEquals(1, result.size()); //Would be 7 and 8, but 8 is not active
    assertEquals("Hai-Fischen", ((Appgroup)result.get(0).getObject()).getName());
  }

  @Test
  void getUserSuggestionsGroupHasNone() {
    setSecurityContext("Test6");
    List<MatchResponse> result = userController.getUserSuggestions();
    assertEquals(0, result.size());
  }

  @Test
  void getMyCurrentMatches() throws IllegalAccessException {
    setSecurityContext("Test3");
    Appgroup group = groupController.getGroupProfile(5);
    setSecurityContext("Test8");
    List<Appgroup> result = userController.getMyCurrentMatches();

    assertEquals(1, result.size());
    assertEquals(group.getGroupId(), result.get(0).getGroupId());
  }

  @Test
  void getMyCurrentMatchesNoMatches() {
    setSecurityContext("Test1");
    List<Appgroup> result = userController.getMyCurrentMatches();
    assertEquals(0, result.size());
  }

  @Test
  void matchAnswer() {
    setSecurityContext("Test3");
    List<Appgroup> result = new ArrayList<>();
    assertEquals(result, userController.getMyCurrentMatches());

    MatchAnswer answer = new MatchAnswer();
    answer.setUserId(3); // Group 7 has true in groupAccept for User 3
    answer.setGroupId(7);
    answer.setAnswer(true);

    boolean isAnswerSet = userController.matchAnswer(answer);
    result = userController.getMyCurrentMatches();

    assertEquals(true, isAnswerSet);
    assertEquals(1, result.size());
    assertEquals("TestGruppe", result.get(0).getName());
  }

  @Test
  void matchAnswerNull() {
    MatchAnswer answer = null;

    assertEquals(false, userController.matchAnswer(answer));
  }
}