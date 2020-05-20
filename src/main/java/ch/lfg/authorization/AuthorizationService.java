package ch.lfg.authorization;

import ch.lfg.entity.Appuser;
import ch.lfg.entity.Userlogin;
import ch.lfg.repository.LoginRepository;
import ch.lfg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

/**
 * Handles specific authorization concerns for the controllers
 */

@Service
public class AuthorizationService implements UserDetailsService {

  @Autowired
  private LoginRepository loginRepository;
  @Autowired
  private UserService userService;

  public Userlogin register(Userlogin login) {
    return loginRepository.save(login);
  }

  /**
   * Function used in Login process to extract User from database with provided username in authorizationrequest
   * @param username priveded by login request
   * @return UserDetails for tokenhandling
   */
  @Override
  public UserDetails loadUserByUsername(String username) {
    Userlogin login = loginRepository.findByUsername(username);
    User user = new User(login.getUsername(), login.getPassword(), new ArrayList<>());
    return user;
  }

  /**
   * Extracts Login details from Token and provides the corresponding user if it exists
   * @return User belonging to Logindata
   */
  public Userlogin getUserFromToken() {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String username;
    if (principal instanceof UserDetails) {
      username = ((UserDetails) principal).getUsername();
    } else {
      username = principal.toString();
    }
    Userlogin user = loginRepository.findByUsername(username);
    return user;
  }

  /**
   * Provides the User with the LoginId in the Token
   * @return User
   */
  public Appuser getUserByLoginId() {
    long loginId = getUserFromToken().getLoginId();
    return userService.getUserByLoginId(loginId);
  }
}
