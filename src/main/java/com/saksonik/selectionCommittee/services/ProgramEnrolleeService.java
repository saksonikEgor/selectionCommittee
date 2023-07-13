package com.saksonik.selectionCommittee.services;

import com.saksonik.selectionCommittee.models.Enrollee;
import com.saksonik.selectionCommittee.models.Program;
import com.saksonik.selectionCommittee.models.ProgramEnrollee;
import com.saksonik.selectionCommittee.models.Subject;
import com.saksonik.selectionCommittee.repositories.ProgramEnrolleeRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProgramEnrolleeService {
    private final ProgramEnrolleeRepository programEnrolleeRepository;

    @Autowired
    public ProgramEnrolleeService(ProgramEnrolleeRepository programEnrolleeRepository) {
        this.programEnrolleeRepository = programEnrolleeRepository;
    }

    public List<ProgramEnrollee> getAllProgramEnrolleeByEnrolleeAndExamResultAboveZero(Enrollee enrollee) {
        return programEnrolleeRepository.findAllByEnrolleeContainsAndExamResultAboveZero(enrollee);
    }

    @Transactional()
    public void saveProgramEnrollee(Enrollee enrollee, Program program) {
        ProgramEnrollee programEnrollee = new ProgramEnrollee();

        programEnrollee.setEnrollee(enrollee);
        programEnrollee.setProgram(program);
        programEnrollee.setExamResult(-1);

        programEnrolleeRepository.save(programEnrollee);
    }

    @Transactional()
    public void deleteProgramEnrollee(Enrollee enrollee, Program program) {
        programEnrolleeRepository.deleteProgramEnrolleeByEnrolleeAndProgram(enrollee, program);
    }

    @Transactional
    public void setAdditionalTestResultByProgramAndEnrollee(Program program, Enrollee enrollee,
                                                            int result) {
        ProgramEnrollee programEnrollee =
                programEnrolleeRepository.findProgramEnrolleeByEnrolleeAndProgram(enrollee, program);

        programEnrollee.setExamResult(result);
        programEnrolleeRepository.save(programEnrollee);
    }

    public List<ProgramEnrollee> getAllByProgram(Program program) {
        return programEnrolleeRepository.findAllByProgram(program);
    }

    private List<Program> getAllProgramsThatTheEnrolleeMustLeave(Enrollee enrollee) {
        List<Program> programsOfEnrollee = new ArrayList<>();
        List<Subject> subjectsOfEnrollee = new ArrayList<>();

        enrollee.getProgramEnrollees().forEach(pe -> programsOfEnrollee.add(pe.getProgram()));
        enrollee.getSubjects().forEach(es -> subjectsOfEnrollee.add(es.getSubject()));

        List<Program> result = new ArrayList<>();
        for (Program program : programsOfEnrollee) {
            List<Subject> subjectsOfProgram = new ArrayList<>();

            program.getSubjects().forEach(ps -> subjectsOfProgram.add(ps.getSubject()));

            if (!new HashSet<>(subjectsOfEnrollee).containsAll(subjectsOfProgram))
                result.add(program);
        }
        return result;
    }

    @Transactional
    public void leaveCompetitiveListInWhichTheEnrolleeCannotParticipate(Enrollee enrollee) {
        List<Program> programs = getAllProgramsThatTheEnrolleeMustLeave(enrollee);

        programs.forEach(p -> deleteProgramEnrollee(enrollee, p));

        enrollee.setProgramEnrollees(getAllProgramEnrolleeByEnrolleeAndExamResultAboveZero(enrollee));
        Hibernate.initialize(enrollee.getProgramEnrollees());
    }
}
