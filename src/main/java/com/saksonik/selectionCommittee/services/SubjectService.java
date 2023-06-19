package com.saksonik.selectionCommittee.services;

import com.saksonik.selectionCommittee.models.Enrollee;
import com.saksonik.selectionCommittee.models.EnrolleeSubject;
import com.saksonik.selectionCommittee.models.Subject;
import com.saksonik.selectionCommittee.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class SubjectService {
    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public List<Subject> getAll() {
        return subjectRepository.findAll();
    }

//    public List<EnrolleeSubject> fillTheList(Enrollee enrollee) {
//        List<Subject> subjects = getAll();
//        List<EnrolleeSubject> enrolleeSubjects = enrollee.getSubjects();
//        List<Subject> subjectOfCurrentEnrollee = new ArrayList<>();
//
//        for (EnrolleeSubject enrolleeSubject : enrolleeSubjects)
//            subjectOfCurrentEnrollee.add(enrolleeSubject.getSubject());
//
//        for (Subject subject : subjects) {
//            if (!subjectOfCurrentEnrollee.contains(subject))
//                enrolleeSubjects.add(new EnrolleeSubject())
//        }
//
//    }
}
