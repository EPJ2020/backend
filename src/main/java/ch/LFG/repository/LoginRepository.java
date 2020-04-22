package ch.LFG.repository;

import ch.LFG.entity.Appgroup;
import ch.LFG.entity.Userlogin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Userlogin, Long> {
}
