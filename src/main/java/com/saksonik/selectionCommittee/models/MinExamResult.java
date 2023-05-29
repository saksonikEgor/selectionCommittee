package com.saksonik.selectionCommittee.models;

import jakarta.persistence.*;

@Entity
@Table(name = "exam_min_result")
public class MinExamResult {
    @Id
    @Column(name = "exam_min_result_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int MinExamResultId;

    @OneToOne
    @JoinColumn(name = "program_id", referencedColumnName = "program_id")
    private Program program;

    @Column(name = "min_result")
    private int minResult;

    public MinExamResult() {}

    public MinExamResult(int minExamResultId, Program program, int minResult) {
        MinExamResultId = minExamResultId;
        this.program = program;
        this.minResult = minResult;
    }

    public int getMinExamResultId() {
        return MinExamResultId;
    }

    public void setMinExamResultId(int minExamResultId) {
        MinExamResultId = minExamResultId;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public int getMinResult() {
        return minResult;
    }

    public void setMinResult(int minResult) {
        this.minResult = minResult;
    }

    @Override
    public String toString() {
        return "MinExamResult{" +
                "MinExamResultId=" + MinExamResultId +
                ", program=" + program +
                ", minResult=" + minResult +
                '}';
    }
}
