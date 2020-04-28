package ch.lfg.repository;

import ch.lfg.entity.Appuser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Appuser, Long> {
}
