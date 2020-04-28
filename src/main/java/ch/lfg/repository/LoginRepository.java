package ch.lfg.repository;

import ch.lfg.entity.Userlogin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface LoginRepository extends JpaRepository<Userlogin, Long>, QueryByExampleExecutor<Userlogin> {
}
