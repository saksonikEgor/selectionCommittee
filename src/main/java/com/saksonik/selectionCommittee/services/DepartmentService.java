package com.saksonik.selectionCommittee.services;

import com.saksonik.selectionCommittee.models.Department;
import com.saksonik.selectionCommittee.models.Program;
import com.saksonik.selectionCommittee.repositories.DepartmentRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    public List<Program> getProgramsOfDepartment(int id) {
        Optional<Department> department = departmentRepository.findById(id);

        if(department.isPresent()) {
            Hibernate.initialize(department.get().getPrograms());
            return department.get().getPrograms();
        }
        return Collections.emptyList();
    }

    public Department getById(int id) {
        return departmentRepository.findById(id).orElse(null);
    }
}
