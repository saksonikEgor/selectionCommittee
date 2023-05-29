package com.saksonik.selectionCommittee.services;

import com.saksonik.selectionCommittee.models.Enrollee;
import com.saksonik.selectionCommittee.models.Program;
import com.saksonik.selectionCommittee.repositories.EnrolleeRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class EnrolleeService {
    private final EnrolleeRepository enrolleeRepository;

    @Autowired
    public EnrolleeService(EnrolleeRepository enrolleeRepository) {
        this.enrolleeRepository = enrolleeRepository;
    }

    public List<Enrollee> getEnrolleesByProgram(Program program) {
        System.out.println("class = " + program.getClass());
        System.out.println(program.toString());

        List<Enrollee> enrollees = enrolleeRepository.findAllByProgramContains(program);

        enrollees.forEach(e -> {
            Hibernate.initialize(e.getAchievements());
            Hibernate.initialize(e.getSubjects());
        });

        return enrollees;
    }

    public List<Enrollee> findAllWhoDidNotPassByProgram(Program program) {
        return enrolleeRepository.findAllWhoDidNotPassByProgram(program);
    }
}
