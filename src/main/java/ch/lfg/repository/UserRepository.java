package ch.lfg.repository;

import ch.lfg.entity.Appuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface UserRepository extends JpaRepository<Appuser, Long>, QueryByExampleExecutor<Appuser> {
}
