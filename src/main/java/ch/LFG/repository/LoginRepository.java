package ch.lfg.repository;

import ch.lfg.entity.Userlogin;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This is the JPArepository abstraction with CRUD methods that are getting translated into Database queries.
 */
public interface LoginRepository extends JpaRepository<Userlogin, Long> {

  /**
   * @param username Expects a username in String format
   * @return Returns a UserLogin Object with the given username
   */
  Userlogin findByUsername(String username);
}
