package ch.lfg.controller;

import ch.lfg.entity.MatchAnswer;
import ch.lfg.entity.Appgroup;
import ch.lfg.entity.Appuser;
import ch.lfg.entity.MatchResponse;
import ch.lfg.entity.Userlogin;
import ch.lfg.exception.FailedLoginException;
import ch.lfg.exception.RegistrationException;
import ch.lfg.authorization.AuthorizationService;
import ch.lfg.service.UserService;
import ch.lfg.authorization.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * See http://152.96.56.38:8080/swagger-ui.html for documentation
 */
@RestController
@RequestMapping("/User")
public class UserController {

  @Autowired
  private UserService userService;
  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtUtil jwtTokenUtil;

  @Autowired
  private AuthorizationService authorizationService;
  @Autowired
  private PasswordEncoder bcryptPasswordEncoder;

  public UserController() {
  }

  //further commands see https://github.com/swagger-api/swagger-core/wiki/Swagger-2.X---Annotations#OpenAPIDefinition
  @Operation(summary = "Register User", description = "Register a new User")
  @RequestMapping(value = "/register", method = RequestMethod.POST)
  public String register(@RequestBody Userlogin login) throws RegistrationException {
    if (login.getPassword() == null || login.getUsername() == null) {
      throw new RegistrationException("username or password is missing!");
    }
    try {
      UserDetails nameAlreadyexists = authorizationService.loadUserByUsername(login.getUsername());
      throw new RegistrationException("user already exists!");
    } catch (NullPointerException e) {
      login.setPassword(bcryptPasswordEncoder.encode(login.getPassword()));
      Userlogin savedLogin = authorizationService.register(login);
      return getToken(savedLogin);
    } catch (IllegalArgumentException e) {
      throw new RegistrationException("username or password is missing!");
    }
  }

  @Operation(summary = "Login", description = "Login with existing user")
  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public String login(@RequestBody Userlogin login) throws FailedLoginException {
    if (login.getPassword() == null
            || login.getUsername() == null
            || login.getUsername() == "") {
      throw new FailedLoginException("username or password is missing!");
    }
    try {
      String username = login.getUsername();
      String password = login.getPassword();
      Authentication auth = new UsernamePasswordAuthenticationToken(username, password);
      authenticationManager.authenticate(auth);
    } catch (NullPointerException e) {
      throw new FailedLoginException("wrong username or password!");
    } catch (BadCredentialsException e) {
      throw new FailedLoginException("wrong username and password!");
    }
    return getToken(login);
  }

  @Operation(summary = "Get User by id", description = "Get one user by his ID")
  @RequestMapping(method = RequestMethod.GET)
  public Appuser getUserProfile() {
    Userlogin login = authorizationService.getUserFromToken();
    Appuser user = userService.getUserByLoginId(login.getLoginId());
    return user;
  }

  @Operation(summary = "Create new User", description = "Create new User")
  @RequestMapping(method = RequestMethod.POST)
  public Appuser setUserProfile(@RequestBody Appuser user) {
    user.setLoginId(authorizationService.getUserFromToken().getLoginId());
    return userService.setUserProfile(user);
  }

  @Operation(summary = "Change User", description = "Change User")
  @RequestMapping(value = "/update", method = RequestMethod.PATCH)
  public Appuser updateUserProfile(@RequestBody Appuser user) {
    try {
      Userlogin login = authorizationService.getUserFromToken();
      user.setLoginId(login.getLoginId());
      user.setUserId(login.getUser().getUserId());
      return userService.updateUserProfile(user);
    } catch (Exception e) {
      throw new IllegalArgumentException("User doesn't exist");
    }
  }

  @Operation(summary = "Get Groups from User by userId",
      description = "Get all the groups of a user by his userId")
  @RequestMapping(value = "/MyGroups", method = RequestMethod.GET)
  public List<Appgroup> getMyGroups() {
    Userlogin login = authorizationService.getUserFromToken();
    Appuser user = userService.getUserByLoginId(login.getLoginId());
    return user.getGroups();
  }

  @Operation(summary = "Get Match suggestions for User",
      description = "Get Match Suggestions of a User by his userId")
  @RequestMapping(value = "/Suggestions", method = RequestMethod.GET)
  public List<MatchResponse> getUserSuggestions() {
    Userlogin login = authorizationService.getUserFromToken();
    long userid = userService.getUserByLoginId(login.getLoginId()).getUserId();
    return userService.getMatchSuggestion(userid);
  }

  @Operation(summary = "Get Matches of User",
      description = "Get the Matches of a User by his userId, where User and Group swiped yes")
  @RequestMapping(value = "/Matches", method = RequestMethod.GET)
  public List<Appgroup> getMyCurrentMatches() {
    Userlogin login = authorizationService.getUserFromToken();
    Appuser user = userService.getUserByLoginId(login.getLoginId());
    return userService.getMyCurrentMatches(user);
  }

  @Operation(summary = "Match Answer", description = "Give the answer for a proposed match")
  @RequestMapping(value = "/MatchesAnswer", method = RequestMethod.POST)
  public Boolean matchAnswer(@RequestBody MatchAnswer match) {
    return userService.setMatchAnswer(match);
  }

  private String getToken(Userlogin login) {
    UserDetails userDetails = authorizationService.loadUserByUsername(login.getUsername());
    String jwt = jwtTokenUtil.generateToken(userDetails);
    return jwt;
  }
}
