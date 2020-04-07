package ch.LFG.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "socialskill")
public class SocialSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skillid")
    private long skillId;
    @Column(name = "skillname")
    private String skillName;
    @OneToMany(mappedBy = "socialskill")
    List<SocialSkillRating> ratings;


    public SocialSkill(){}

    public SocialSkill(String skillName) {
        this.skillName = skillName;
    }

    public long getSkillId() {
        return skillId;
    }

    public void setSkillId(long skillId) {
        this.skillId = skillId;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public List<SocialSkillRating> getRatings() {
        return ratings;
    }

    public void setRatings(List<SocialSkillRating> ratings) {
        this.ratings = ratings;
    }
}
