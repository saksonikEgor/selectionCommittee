package com.saksonik.selectionCommittee.services;

import com.saksonik.selectionCommittee.models.Enrollee;
import com.saksonik.selectionCommittee.models.Program;
import com.saksonik.selectionCommittee.models.ProgramEnrollee;
import com.saksonik.selectionCommittee.repositories.ProgramEnrolleeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProgramEnrolleeService {
    private final ProgramEnrolleeRepository programEnrolleeRepository;

    public ProgramEnrolleeService(ProgramEnrolleeRepository programEnrolleeRepository) {
        this.programEnrolleeRepository = programEnrolleeRepository;
    }

    public List<ProgramEnrollee> getAllProgramEnrolleeByEnrollee(Enrollee enrollee) {
        return programEnrolleeRepository.findAllByEnrolleeContains(enrollee);
    }

    @Transactional()
    public void saveProgramEnrollee(Enrollee enrollee, Program program) {
        ProgramEnrollee programEnrollee = new ProgramEnrollee();

        programEnrollee.setEnrollee(enrollee);
        programEnrollee.setProgram(program);
        programEnrollee.setExamResult(-1);

        programEnrolleeRepository.save(programEnrollee);
    }
}
