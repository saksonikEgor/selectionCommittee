package com.saksonik.selectionCommittee.services;

import com.saksonik.selectionCommittee.models.*;
import com.saksonik.selectionCommittee.repositories.ProgramRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ProgramService {
    private final ProgramRepository programRepository;

    @Autowired
    public ProgramService(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    public List<Program> getAll() {
        return programRepository.findAll();
    }

    public Program getById(int id) {
        Optional<Program> program = programRepository.findById(id);

        if (program.isPresent()) {
            Hibernate.initialize(program.get().getSubjects());
            return program.get();
        }
        return null;
    }

    public List<Program> getAllBySubjectsContaining(List<Subject> subjects) {
        List<Program> result = new ArrayList<>();
        List<Program> programs = programRepository.findAll();

        for (Program program : programs) {
            List<ProgramSubject> programSubjects = program.getSubjects();

            List<Subject> subjectsOfProgram = new ArrayList<>();
            for (ProgramSubject programSubject : programSubjects) {
                subjectsOfProgram.add(programSubject.getSubject());
            }

            if (subjects.containsAll(subjectsOfProgram)) {
                result.add(program);
            }
        }
        return result;
    }

    public List<Program> getAllByEnrolleeParticipate(Enrollee enrollee) {
        return programRepository.findAllByEnrolleeParticipate(enrollee);
    }

    public List<Program> getAllByEnrolleeNotParticipate(Enrollee enrollee) {
        List<EnrolleeSubject> enrolleeSubjects = enrollee.getSubjects();
        List<Subject> subjectsOfEnrollee = new ArrayList<>();

        for (EnrolleeSubject enrolleeSubject : enrolleeSubjects)
            subjectsOfEnrollee.add(enrolleeSubject.getSubject());

        List<Program> availableProgramsForEnrollee = getAllBySubjectsContaining(subjectsOfEnrollee);
        List<Program> programsForEnrollee = getAllByEnrolleeParticipate(enrollee);

        availableProgramsForEnrollee.removeAll(programsForEnrollee);

        return availableProgramsForEnrollee;
    }
}
