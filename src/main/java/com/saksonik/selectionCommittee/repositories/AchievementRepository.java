package com.saksonik.selectionCommittee.repositories;

import com.saksonik.selectionCommittee.models.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AchievementRepository extends JpaRepository<Achievement, Integer> {
    Achievement findByNameAchievement(String name);
}
