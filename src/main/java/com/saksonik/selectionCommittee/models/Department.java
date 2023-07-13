package com.saksonik.selectionCommittee.models;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;

import java.util.List;

@Entity
@Table(name = "department")
public class Department {
    @Id
    @Column(name = "department_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int departmentId;
    @Column(name = "name_department")
    private String nameDepartment;

    @OneToMany(mappedBy = "department")
    private List<Program> programs;

    public Department() {
    }

    public Department(int departmentId, String nameDepartment) {
        this.departmentId = departmentId;
        this.nameDepartment = nameDepartment;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getNameDepartment() {
        return nameDepartment;
    }

    public void setNameDepartment(String nameDepartment) {
        this.nameDepartment = nameDepartment;
    }

    public List<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(List<Program> programs) {
        this.programs = programs;
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentId=" + departmentId +
                ", nameDepartment='" + nameDepartment + '\'' +
                '}';
    }
}
