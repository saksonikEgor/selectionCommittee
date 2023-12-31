package com.saksonik.selectionCommittee.services;

import com.saksonik.selectionCommittee.models.*;
import com.saksonik.selectionCommittee.repositories.EnrolleeRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(readOnly = true)
public class EnrolleeService {
    private final EnrolleeRepository enrolleeRepository;

    @Autowired
    public EnrolleeService(EnrolleeRepository enrolleeRepository) {
        this.enrolleeRepository = enrolleeRepository;
    }

    public List<Enrollee> getEnrolleesByProgram(Program program) {
        List<Enrollee> enrollees = enrolleeRepository.findAllByProgramContains(program);

        enrollees.forEach(e -> {
            Hibernate.initialize(e.getAchievements());
            Hibernate.initialize(e.getSubjects());
        });

        return enrollees;
    }

    public Optional<Enrollee> getEnrolleeByEmailAndPassword(String email, String password) {
        return enrolleeRepository.findEnrolleeByEmailAndPassword(email, password);
    }

    public Optional<Enrollee> getEnrolleeByEmail(String email) {
        return enrolleeRepository.findEnrolleeByEmail(email);
    }

    public Optional<Enrollee> getEnrolleeByPhoneNumber(String phoneNumber) {
        return enrolleeRepository.findEnrolleeByPhoneNumber(phoneNumber);
    }

    public List<Enrollee> findAllWhoDidNotPassByProgram(Program program) {
        return enrolleeRepository.findAllWhoDidNotPassByProgram(program);
    }

    public Enrollee getEnrolleeById(int id) {
        return enrolleeRepository.findById(id).get();
    }

    @Transactional()
    public void saveEnrollee(Enrollee enrollee) {
        enrollee.setAchievements(Collections.emptyList());
        enrollee.setProgramEnrollees(Collections.emptyList());
        enrollee.setSubjects(Collections.emptyList());
        enrolleeRepository.save(enrollee);
    }

    public List<Enrollee> getAllWhoDidNotPassedAdditionalTestByProgram(Program program) {
        return enrolleeRepository.findAllWhoDidNotPassAdditionalTestByProgram(program);
    }

    @Transactional()
    public void saveChangedEnrollee(Enrollee enrollee) {
        enrolleeRepository.save(enrollee);
    }

    public List<Enrollee> getAll() {
        return enrolleeRepository.findAll();
    }
}
