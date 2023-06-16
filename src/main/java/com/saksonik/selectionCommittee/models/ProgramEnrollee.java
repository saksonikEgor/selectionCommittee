package com.saksonik.selectionCommittee.models;

import jakarta.persistence.*;

@Entity
@Table(name = "program_enrollee")
public class ProgramEnrollee {
    @Id
    @Column(name = "program_enrollee_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int programEnrolleeId;

    @ManyToOne
    @JoinColumn(name = "program_id", referencedColumnName = "program_id")
    private Program program;

    @ManyToOne
    @JoinColumn(name = "enrollee_id", referencedColumnName = "enrollee_id")
    private Enrollee enrollee;

    @Column(name = "exam_result")
    private int examResult;

    public ProgramEnrollee() {}

    public ProgramEnrollee(int programEnrolleeId, Program program, Enrollee enrollee) {
        this.programEnrolleeId = programEnrolleeId;
        this.program = program;
        this.enrollee = enrollee;
    }

    public int getProgramEnrolleeId() {
        return programEnrolleeId;
    }

    public void setProgramEnrolleeId(int programEnrolleeId) {
        this.programEnrolleeId = programEnrolleeId;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public Enrollee getEnrollee() {
        return enrollee;
    }

    public void setEnrollee(Enrollee enrollee) {
        this.enrollee = enrollee;
    }

    public int getExamResult() {
        return examResult;
    }

    public void setExamResult(int examResult) {
        this.examResult = examResult;
    }

    @Override
    public String toString() {
        return "ProgramEnrollee{" +
                "programEnrolleeId=" + programEnrolleeId +
                ", program=" + program +
                ", enrollee=" + enrollee +
                '}';
    }
}
