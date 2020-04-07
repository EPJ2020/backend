package ch.LFG.repository;

import ch.LFG.entity.Group;
import ch.LFG.entity.SocialSkill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialSkillRepository extends JpaRepository<SocialSkill, Long> {
}
