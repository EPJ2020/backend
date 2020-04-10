package ch.LFG.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "socialskillrating")
public class SocialSkillRating {

    @Id
    @Column(name="ratingId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ratingId;

    @ManyToOne
    @JoinColumn(name = "skillid")
    private SocialSkill skill;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    private int rating;

    public SocialSkillRating() {
    }

    public SocialSkillRating(SocialSkill skill, User user, int rating) {
        this.skill = skill;
        this.user = user;
        this.rating = rating;
    }

    public SocialSkill getSkill() {
        return skill;
    }

    public void setSkill(SocialSkill skill) {
        this.skill = skill;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }


}