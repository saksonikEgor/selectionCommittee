package com.saksonik.selectionCommittee.services;

import com.saksonik.selectionCommittee.models.Program;
import com.saksonik.selectionCommittee.repositories.ProgramRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        //return programRepository.findById(id).orElse(null);
    }
}
