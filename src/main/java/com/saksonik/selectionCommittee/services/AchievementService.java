package com.saksonik.selectionCommittee.services;

import com.saksonik.selectionCommittee.models.Achievement;
import com.saksonik.selectionCommittee.repositories.AchievementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class AchievementService {
    private final AchievementRepository achievementRepository;

    @Autowired
    public AchievementService(AchievementRepository achievementRepository) {
        this.achievementRepository = achievementRepository;
    }

    public List<Achievement> getAll() {
        return achievementRepository.findAll();
    }

    public List<Achievement> getAllByNames(List<String> names) {
        List<Achievement> achievements = new ArrayList<>();

        for (String name : names)
            if (!name.isEmpty())
                achievements.add(achievementRepository.findByNameAchievement(name));

        return achievements;
    }
}
