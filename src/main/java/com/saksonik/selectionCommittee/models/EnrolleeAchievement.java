package com.saksonik.selectionCommittee.models;

import jakarta.persistence.*;

@Entity
@Table(name = "enrollee_achievement")
public class EnrolleeAchievement {
    @Id
    @Column(name = "enrollee_achiev_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int enrolleeAchievementId;

    @Column(name = "enrollee_id")
    private int enrolleeId;

    @Column(name = "achievement_id")
    private int achievementId;

    public EnrolleeAchievement() {
    }

    public int getEnrolleeAchievementId() {
        return enrolleeAchievementId;
    }

    public void setEnrolleeAchievementId(int enrolleeAchievementId) {
        this.enrolleeAchievementId = enrolleeAchievementId;
    }

    public int getEnrolleeId() {
        return enrolleeId;
    }

    public void setEnrolleeId(int enrolleeId) {
        this.enrolleeId = enrolleeId;
    }

    public int getAchievementId() {
        return achievementId;
    }

    public void setAchievementId(int achievementId) {
        this.achievementId = achievementId;
    }

    @Override
    public String toString() {
        return "EnrolleeAchievement{" +
                "enrolleeAchievementId=" + enrolleeAchievementId +
                ", enrolleeId=" + enrolleeId +
                ", achievementId=" + achievementId +
                '}';
    }
}
