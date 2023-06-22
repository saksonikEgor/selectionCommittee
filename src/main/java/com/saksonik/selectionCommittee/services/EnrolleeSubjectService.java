package com.saksonik.selectionCommittee.services;

import com.saksonik.selectionCommittee.models.Enrollee;
import com.saksonik.selectionCommittee.models.EnrolleeSubject;
import com.saksonik.selectionCommittee.models.Subject;
import com.saksonik.selectionCommittee.repositories.EnrolleeSubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class EnrolleeSubjectService {
    private final EnrolleeSubjectRepository enrolleeSubjectRepository;

    @Autowired
    public EnrolleeSubjectService(EnrolleeSubjectRepository enrolleeSubjectRepository) {
        this.enrolleeSubjectRepository = enrolleeSubjectRepository;
    }

    @Transactional
    public void resetEnrolleeSubjectsForEnrollee(Enrollee enrollee, List<Subject> subjects) {
        enrolleeSubjectRepository.deleteAllByEnrollee(enrollee);

        List<EnrolleeSubject> enrolleeSubjects = new ArrayList<>();

        for (Subject subject : subjects) {
            EnrolleeSubject enrolleeSubject = new EnrolleeSubject();

            enrolleeSubject.setEnrollee(enrollee);
            enrolleeSubject.setSubject(subject);

            enrolleeSubjects.add(enrolleeSubject);
        }

        enrolleeSubjectRepository.saveAll(enrolleeSubjects);
    }

    @Transactional
    public void setNewResults(Enrollee enrollee, Integer[] results) {
        List<EnrolleeSubject> enrolleeSubjectsOfEnrollee = enrollee.getSubjects();

        for (int i = 0; i < enrolleeSubjectsOfEnrollee.size(); i++)
            enrolleeSubjectsOfEnrollee.get(i).setResult(results[i]);

        enrolleeSubjectRepository.saveAll(enrolleeSubjectsOfEnrollee);
    }
}
