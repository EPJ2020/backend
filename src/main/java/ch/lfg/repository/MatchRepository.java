package ch.lfg.repository;

import ch.lfg.entity.Match;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface MatchRepository extends JpaRepository<Match, Long>, QueryByExampleExecutor<Match> {
}
