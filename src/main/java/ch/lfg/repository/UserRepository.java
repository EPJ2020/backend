package ch.lfg.repository;

import ch.lfg.entity.Appuser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * This is the JPArepository abstraction with CRUD methods that are getting translated into Database queries.
 */
public interface UserRepository extends JpaRepository<Appuser, Long> {

  /**
   * @param id Database identifier of the login object for which we want a appuser object
   * @return Returns an appuserobject with the specified loginid as foreignkey.
   */
  @Query("SELECT a FROM Appuser a WHERE a.loginId = ?1")
  Appuser getUserByLoginId(long id);

}
