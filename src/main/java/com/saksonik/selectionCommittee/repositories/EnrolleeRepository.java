package com.saksonik.selectionCommittee.repositories;

import com.saksonik.selectionCommittee.models.Enrollee;
import com.saksonik.selectionCommittee.models.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrolleeRepository extends JpaRepository<Enrollee, Integer> {
    @Query(
            "select e from Enrollee e " +
                    "inner join ProgramEnrollee pe on e = pe.enrollee " +
                    "inner join Program p on p = pe.program " +
                    "where pe.program = :program"
    )
    List<Enrollee> findAllByProgramContains(@Param("program") Program program);

    Optional<Enrollee> findEnrolleeByEmailAndPassword(String email, String password);

    @Query(
            "select e from Enrollee e " +
                    "inner join EnrolleeSubject es on e = es.enrollee " +
                    "inner join ProgramSubject ps on es.subject = ps.subject " +
                    "where es.result < ps.minResult " +
                    "and ps.program = :program"
    )
    List<Enrollee> findAllWhoDidNotPassByProgram(@Param("program") Program program);
}
