package com.saksonik.selectionCommittee.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.util.List;

@Entity
@Table(name = "enrollee")
public class Enrollee {
    @Id
    @Column(name = "enrollee_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int enrolleeId;
    @Column(name = "name_enrollee")
    private String nameEnrollee;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    @Email
    private String email;

    @Column(name = "passport_number")
    private int passportNumber;

    @Column(name = "certificate_number")
    private int certificateNumber;

    @Column(name = "statement_number")
    private int statimentNumber;

    @Column(name = "photo_number")
    private int photoNumber;

    @Column(name = "benefit_number")
    private int benefitNumber;

    @Column(name = "army_number")
    private int armyNumber;

    @Column(name = "medition_number")
    private int medicalNumber;

    @ManyToMany
    @JoinTable(
            name = "enrollee_achievement",
            joinColumns = @JoinColumn(name = "enrollee_id"),
            inverseJoinColumns = @JoinColumn(name = "achievement_id")
    )
    private List<Achievement> achievements;

    @OneToMany(mappedBy = "enrollee")
    private List<ProgramEnrollee> programEnrollees;

    @OneToMany(mappedBy = "enrollee")
    private List<EnrolleeSubject> subjects;

    public Enrollee() {
    }

    public Enrollee(int enrolleeId, String nameEnrollee) {
        this.enrolleeId = enrolleeId;
        this.nameEnrollee = nameEnrollee;
    }

    public int getEnrolleeId() {
        return enrolleeId;
    }

    public void setEnrolleeId(int enrolleeId) {
        this.enrolleeId = enrolleeId;
    }

    public String getNameEnrollee() {
        return nameEnrollee;
    }

    public void setNameEnrollee(String nameEnrollee) {
        this.nameEnrollee = nameEnrollee;
    }

    public List<Achievement> getAchievements() {
        return achievements;
    }

    public void setAchievements(List<Achievement> achievements) {
        this.achievements = achievements;
    }

    public List<ProgramEnrollee> getProgramEnrollees() {
        return programEnrollees;
    }

    public void setProgramEnrollees(List<ProgramEnrollee> programEnrollees) {
        this.programEnrollees = programEnrollees;
    }

    public List<EnrolleeSubject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<EnrolleeSubject> subjects) {
        this.subjects = subjects;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(int passportNumber) {
        this.passportNumber = passportNumber;
    }

    public int getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(int certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public int getStatimentNumber() {
        return statimentNumber;
    }

    public void setStatimentNumber(int statimentNumber) {
        this.statimentNumber = statimentNumber;
    }

    public int getPhotoNumber() {
        return photoNumber;
    }

    public void setPhotoNumber(int photoNumber) {
        this.photoNumber = photoNumber;
    }

    public int getBenefitNumber() {
        return benefitNumber;
    }

    public void setBenefitNumber(int benefitNumber) {
        this.benefitNumber = benefitNumber;
    }

    public int getArmyNumber() {
        return armyNumber;
    }

    public void setArmyNumber(int armyNumber) {
        this.armyNumber = armyNumber;
    }

    public int getMedicalNumber() {
        return medicalNumber;
    }

    public void setMedicalNumber(int medicalNumber) {
        this.medicalNumber = medicalNumber;
    }

    @Override
    public String toString() {
        return "Enrollee{" +
                "enrolleeId=" + enrolleeId +
                ", nameEnrollee='" + nameEnrollee + '\'' +
                '}';
    }
}
