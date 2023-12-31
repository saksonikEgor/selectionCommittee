package com.saksonik.selectionCommittee.repositories;

import com.saksonik.selectionCommittee.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {
}
