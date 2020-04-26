package ch.LFG.repository;

import ch.LFG.entity.Appgroup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface GroupRepository extends JpaRepository<Appgroup, Long>, QueryByExampleExecutor<Appgroup> {
}
