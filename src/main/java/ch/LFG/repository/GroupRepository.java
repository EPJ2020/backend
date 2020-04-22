package ch.LFG.repository;

import ch.LFG.entity.Appgroup;
import ch.LFG.entity.Appuser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Appgroup, Long> {
}
