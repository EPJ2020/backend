package ch.lfg.repository;

import ch.lfg.entity.Userlogin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Userlogin, Long> {
}
