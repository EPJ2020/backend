package ch.LFG.repository;

import ch.LFG.entity.Group;
import ch.LFG.entity.UserContact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserContactRepository extends JpaRepository<UserContact, Long> {
}
