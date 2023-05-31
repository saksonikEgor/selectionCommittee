package com.saksonik.selectionCommittee.models;


import jakarta.persistence.*;

@Entity
@Table(name = "enrollee_subject")
public class EnrolleeSubject {
    @Id
    @Column(name = "enrollee_subject_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int enrolleeSubjectId;

    @ManyToOne()
    @JoinColumn(name = "enrollee_id", referencedColumnName = "enrollee_id")
    private Enrollee enrollee;

    @ManyToOne()
    @JoinColumn(name = "subject_id", referencedColumnName = "subject_id")
    private Subject subject;

    @Column(name = "result")
    private int result;

    public EnrolleeSubject() {}

    public EnrolleeSubject(Enrollee enrollee, Subject subject, int result) {
        this.enrollee = enrollee;
        this.subject = subject;
        this.result = result;
    }

    public int getEnrolleeSubjectId() {
        return enrolleeSubjectId;
    }

    public void setEnrolleeSubjectId(int enrolleeSubjectId) {
        this.enrolleeSubjectId = enrolleeSubjectId;
    }

    public Enrollee getEnrollee() {
        return enrollee;
    }

    public void setEnrollee(Enrollee enrollee) {
        this.enrollee = enrollee;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "EnrolleeSubject{" +
                "enrolleeSubjectId=" + enrolleeSubjectId +
                ", enrollee=" + enrollee +
                ", subject=" + subject +
                ", result=" + result +
                '}';
    }
}
