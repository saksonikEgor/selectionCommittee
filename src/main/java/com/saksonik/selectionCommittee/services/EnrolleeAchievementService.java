package com.saksonik.selectionCommittee.services;

import com.saksonik.selectionCommittee.models.Achievement;
import com.saksonik.selectionCommittee.models.Enrollee;
import com.saksonik.selectionCommittee.models.EnrolleeAchievement;
import com.saksonik.selectionCommittee.repositories.EnrolleeAchievementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class EnrolleeAchievementService {
    private final EnrolleeAchievementRepository enrolleeAchievementRepository;

    @Autowired
    public EnrolleeAchievementService(EnrolleeAchievementRepository enrolleeAchievementRepository) {
        this.enrolleeAchievementRepository = enrolleeAchievementRepository;
    }

    @Transactional()
    public void resetEnrolleeAchievementsByEnrolleeAndAchievements(Enrollee enrollee,
                                                                 List<Achievement> achievements) {
        List<EnrolleeAchievement> enrolleeAchievements = new ArrayList<>();

        for (Achievement achievement : achievements) {
            EnrolleeAchievement enrolleeAchievement = new EnrolleeAchievement();

            enrolleeAchievement.setEnrolleeId(enrollee.getEnrolleeId());
            enrolleeAchievement.setAchievementId(achievement.getAchievementId());

            enrolleeAchievements.add(enrolleeAchievement);
        }
        enrolleeAchievementRepository.deleteAllByEnrolleeId(enrollee.getEnrolleeId());
        enrolleeAchievementRepository.saveAll(enrolleeAchievements);
    }
}
