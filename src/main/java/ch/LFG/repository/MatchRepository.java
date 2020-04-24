package ch.LFG.repository;

import ch.LFG.entity.Match;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface MatchRepository extends JpaRepository<Match, Long>, QueryByExampleExecutor<Match> {
}
