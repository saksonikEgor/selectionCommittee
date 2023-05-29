package com.saksonik.selectionCommittee.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "program")
public class Program {
    @Id
    @Column(name = "program_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int programId;

    @Column(name = "name_program")
    private String nameProgram;

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "department_id")
    private Department department;

    @Column(name = "plan")
    private int plan;

    @OneToMany(mappedBy = "program")
    private List<ProgramEnrollee> programEnrollees;

    @OneToMany(mappedBy = "program")
    private List<ProgramSubject> subjects;

    @OneToMany(mappedBy = "program")
    private List<ExamQuestion> examQuestions;

    @OneToOne(mappedBy = "program")
    private MinExamResult minExamResult;

    public Program () {}

    public Program(int programId, String nameProgram, Department department, int plan) {
        this.programId = programId;
        this.nameProgram = nameProgram;
        this.department = department;
        this.plan = plan;
    }

    public int getProgramId() {
        return programId;
    }

    public void setProgramId(int programId) {
        this.programId = programId;
    }

    public String getNameProgram() {
        return nameProgram;
    }

    public void setNameProgram(String nameProgram) {
        this.nameProgram = nameProgram;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public int getPlan() {
        return plan;
    }

    public void setPlan(int plan) {
        this.plan = plan;
    }

    public List<ProgramEnrollee> getProgramEnrollees() {
        return programEnrollees;
    }

    public void setProgramEnrollees(List<ProgramEnrollee> programEnrollees) {
        this.programEnrollees = programEnrollees;
    }

    public List<ExamQuestion> getExamQuestions() {
        return examQuestions;
    }

    public void setExamQuestions(List<ExamQuestion> examQuestions) {
        this.examQuestions = examQuestions;
    }

    public MinExamResult getMinExamResult() {
        return minExamResult;
    }

    public void setMinExamResult(MinExamResult minExamResult) {
        this.minExamResult = minExamResult;
    }

    public List<ProgramSubject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<ProgramSubject> subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "Program{" +
                "programId=" + programId +
                ", nameProgram='" + nameProgram + '\'' +
                ", departmentId=" + department +
                ", plan=" + plan +
                '}';
    }
}
