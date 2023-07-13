package com.saksonik.selectionCommittee.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "achievement")
public class Achievement {
    @Id
    @Column(name = "achievement_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int achievementId;
    @Column(name = "name_achievement")
    private String nameAchievement;
    @Column(name = "bonus")
    private int bonus;

    @ManyToMany(mappedBy = "achievements")
    private List<Enrollee> enrollees;

    public Achievement() {
    }

    public int getAchievementId() {
        return achievementId;
    }

    public void setAchievementId(int achievementId) {
        this.achievementId = achievementId;
    }

    public String getNameAchievement() {
        return nameAchievement;
    }

    public void setNameAchievement(String nameAchievement) {
        this.nameAchievement = nameAchievement;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public List<Enrollee> getEnrollees() {
        return enrollees;
    }

    public void setEnrollees(List<Enrollee> enrollees) {
        this.enrollees = enrollees;
    }

    @Override
    public String toString() {
        return "Achievement{" +
                "achievementId=" + achievementId +
                ", nameAchievement='" + nameAchievement + '\'' +
                ", bonus=" + bonus +
                '}';
    }
}
