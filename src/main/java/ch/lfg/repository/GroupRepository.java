package ch.lfg.repository;

import ch.lfg.entity.Appgroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Appgroup, Long> {
}
