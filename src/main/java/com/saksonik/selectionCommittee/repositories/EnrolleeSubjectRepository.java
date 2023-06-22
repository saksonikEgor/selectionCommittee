package com.saksonik.selectionCommittee.repositories;

import com.saksonik.selectionCommittee.models.Enrollee;
import com.saksonik.selectionCommittee.models.EnrolleeSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrolleeSubjectRepository extends JpaRepository<EnrolleeSubject, Integer> {
    void deleteAllByEnrollee(Enrollee enrollee);
}
