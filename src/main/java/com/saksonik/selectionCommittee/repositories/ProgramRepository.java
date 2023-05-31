package com.saksonik.selectionCommittee.repositories;

import com.saksonik.selectionCommittee.models.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Integer> {
//    @Query("select p from Program p " +
//            "inner join ProgramSubject ps on p = ps.program " +
//            "inner join Subject s on s = ps.subject " +
//            "where ps.subject = :subject")
//    List<Program> findAllBySubjectsContaining();
//
//    List<Program> findAllBy
}
