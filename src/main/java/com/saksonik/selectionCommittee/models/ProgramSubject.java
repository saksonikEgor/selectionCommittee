package com.saksonik.selectionCommittee.models;

import jakarta.persistence.*;

@Entity
@Table(name = "program_subject")
public class ProgramSubject {
    @Id
    @Column(name = "program_subject_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int programSubjectId;

    @ManyToOne()
    @JoinColumn(name = "program_id", referencedColumnName = "program_id")
    private Program program;

    @ManyToOne()
    @JoinColumn(name = "subject_id", referencedColumnName = "subject_id")
    private Subject subject;

    @Column(name = "min_result")
    private int minResult;

    public ProgramSubject(int programSubjectId, Program program, Subject subject, int minResult) {
        this.programSubjectId = programSubjectId;
        this.program = program;
        this.subject = subject;
        this.minResult = minResult;
    }

    public ProgramSubject() {
    }

    public int getProgramSubjectId() {
        return programSubjectId;
    }

    public void setProgramSubjectId(int programSubjectId) {
        this.programSubjectId = programSubjectId;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public int getMinResult() {
        return minResult;
    }

    public void setMinResult(int minResult) {
        this.minResult = minResult;
    }

    @Override
    public String toString() {
        return "ProgramSubject{" +
                "programSubjectId=" + programSubjectId +
                ", program=" + program +
                ", subject=" + subject +
                ", minResult=" + minResult +
                '}';
    }
}
