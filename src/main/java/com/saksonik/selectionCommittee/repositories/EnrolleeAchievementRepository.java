package com.saksonik.selectionCommittee.repositories;

import com.saksonik.selectionCommittee.models.EnrolleeAchievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrolleeAchievementRepository extends JpaRepository<EnrolleeAchievement, Integer> {
    void deleteAllByEnrolleeId(int enrolleeId);
}
