package com.saksonik.selectionCommittee.models;

import jakarta.persistence.*;

@Entity
@Table(name = "exam_questions")
public class ExamQuestion {
    @Id
    @Column(name = "exam_questions_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int examQuestionId;

    @ManyToOne
    @JoinColumn(name = "program_id", referencedColumnName = "program_id")
    private Program program;

    @Column(name = "question")
    private String question;

    public ExamQuestion(int examQuestionId, Program program, String question) {
        this.examQuestionId = examQuestionId;
        this.program = program;
        this.question = question;
    }

    public ExamQuestion() {
    }

    public int getExamQuestionId() {
        return examQuestionId;
    }

    public void setExamQuestionId(int examQuestionId) {
        this.examQuestionId = examQuestionId;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "ExamQuestion{" +
                "examQuestionId=" + examQuestionId +
                ", program=" + program +
                ", question='" + question + '\'' +
                '}';
    }
}
