package com.saksonik.selectionCommittee.repositories;

import com.saksonik.selectionCommittee.models.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Integer> {
}
