package com.saksonik.selectionCommittee.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "subject")
public class Subject {
    @Id
    @Column(name = "subject_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int subject_id;
    @Column(name = "name_subject")
    private String nameSubject;

    @OneToMany(mappedBy = "subject")
    private List<EnrolleeSubject> enrollees;

    @OneToMany(mappedBy = "subject")
    private List<ProgramSubject> programs;

    public Subject(int subject_id, String nameSubject) {
        this.subject_id = subject_id;
        this.nameSubject = nameSubject;
    }

    public Subject() {}

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public String getNameSubject() {
        return nameSubject;
    }

    public void setNameSubject(String nameSubject) {
        this.nameSubject = nameSubject;
    }

    public List<EnrolleeSubject> getEnrollees() {
        return enrollees;
    }

    public void setEnrollees(List<EnrolleeSubject> enrollees) {
        this.enrollees = enrollees;
    }

    public List<ProgramSubject> getPrograms() {
        return programs;
    }

    public void setPrograms(List<ProgramSubject> programs) {
        this.programs = programs;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subject_id=" + subject_id +
                ", name_subject='" + nameSubject + '\'' +
                '}';
    }
}
