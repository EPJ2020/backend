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
    private SocialSkill socialSkill;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    private int rating;

    public SocialSkillRating() {
    }

    public SocialSkillRating(SocialSkill socialSkill, User user, int rating) {
        this.socialSkill = socialSkill;
        this.user = user;
        this.rating = rating;
    }

    public SocialSkill getSkill() {
        return socialSkill;
    }

    public void setSkill(SocialSkill socialSkill) {
        this.socialSkill = socialSkill;
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
