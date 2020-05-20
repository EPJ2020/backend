package ch.lfg.authorization;

import ch.lfg.LfgApplication;
import ch.lfg.entity.Userlogin;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.jdbc.Sql;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = LfgApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql({"classpath:schema.sql", "classpath:data.sql"})
class AuthorizationServiceTest {

  @Autowired
  AuthorizationService authorizationService;

  private Userlogin userlogin = new Userlogin("Test4", "foofoo");

  private void setSecurityContext(String userinfo) {
    var userPrincipal = new User(userinfo, userinfo, new ArrayList<>());
    var auth = new TestingAuthenticationToken(userPrincipal, null, new ArrayList<>());
    auth.setAuthenticated(true);
    SecurityContextHolder.getContext().setAuthentication(auth);
  }

  @Test
  void register() {
    assertEquals(userlogin.getUsername(), authorizationService.register(userlogin).getUsername());
  }

  @Test
  void registerWithNull() {
    assertThrows(InvalidDataAccessApiUsageException.class, () -> {
      authorizationService.register(null);
    });
  }

  @Test
  void loadUserByUsername() {
    UserDetails userDetails = authorizationService.loadUserByUsername(userlogin.getUsername());
    assertEquals(userlogin.getUsername(), userDetails.getUsername());
    assertEquals("{bcrypt}$2a$10$GuZhKZTGpUZKtKdsEst8nu8UKciDzJxMkEtr/M6GTlyPkJpFjH6E.", userDetails.getPassword());
  }

  @Test
  void loadUserByUsernameNull() {
    userlogin.setUsername(null);
    assertThrows(NullPointerException.class, () -> {
      authorizationService.loadUserByUsername(userlogin.getUsername());
    });
  }

  @Test
  void loadUserByUsernameDoesntExist() {
    userlogin.setUsername("usertest");
    assertThrows(NullPointerException.class, () -> {
      authorizationService.loadUserByUsername(userlogin.getUsername());
    });
  }

  @Test
  void getUserFromToken() {
    setSecurityContext("Test7");
    Userlogin login = authorizationService.getUserFromToken();
    assertEquals("Test7", login.getUsername());
  }

}