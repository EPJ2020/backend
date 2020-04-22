package ch.LFG.repository;

import ch.LFG.entity.Appgroup;
import ch.LFG.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Long> {
}
