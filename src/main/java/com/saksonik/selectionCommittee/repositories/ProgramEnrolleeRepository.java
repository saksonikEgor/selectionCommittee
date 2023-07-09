package com.saksonik.selectionCommittee.repositories;

import com.saksonik.selectionCommittee.models.Enrollee;
import com.saksonik.selectionCommittee.models.Program;
import com.saksonik.selectionCommittee.models.ProgramEnrollee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgramEnrolleeRepository extends JpaRepository<ProgramEnrollee, Integer> {
    @Query("select pe from ProgramEnrollee pe " +
            "inner join Enrollee e on e = pe.enrollee " +
            "where pe.enrollee = :enrollee and pe.examResult >= 0")
    List<ProgramEnrollee> findAllByEnrolleeContainsAndExamResultAboveZero(@Param("enrollee") Enrollee enrollee);

    void deleteProgramEnrolleeByEnrolleeAndProgram(Enrollee enrollee, Program program);

    ProgramEnrollee findProgramEnrolleeByEnrolleeAndProgram(Enrollee enrollee, Program program);

    List<ProgramEnrollee> findAllByProgram(Program program);
}
