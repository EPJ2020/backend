package ch.lfg.repository;

import ch.lfg.entity.Appgroup;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This is the JPArepository abstraction with CRUD methods that are getting translated into Database queries.
 */
public interface GroupRepository extends JpaRepository<Appgroup, Long> {

}
