package ch.LFG.repository;

import ch.LFG.entity.Appgroup;
import ch.LFG.entity.GroupContact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupContactRepository extends JpaRepository<GroupContact, Long> {
}
