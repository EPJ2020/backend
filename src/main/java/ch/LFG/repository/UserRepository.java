package ch.LFG.repository;

import ch.LFG.entity.Appuser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Appuser, Long> {
}
