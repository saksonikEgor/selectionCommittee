package com.saksonik.selectionCommittee.repositories;

import com.saksonik.selectionCommittee.models.Enrollee;
import com.saksonik.selectionCommittee.models.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Integer> {
    @Query("select p from Program p " +
            "inner join ProgramEnrollee pe on p = pe.program " +
            "inner join Enrollee e on e = pe.enrollee " +
            "where pe.enrollee = :enrollee")
    List<Program> findAllByEnrolleeParticipate(@Param("enrollee") Enrollee enrollee);
}
