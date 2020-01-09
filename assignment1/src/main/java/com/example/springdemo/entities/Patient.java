package com.example.springdemo.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "patient_id", unique = true, nullable = false)
    private Integer patientId;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "birth_date", nullable = false, length = 45)
    private String birthDate;

    @Column(name = "gender")
    private String gender;

    @Column(name = "address")
    private String address;

    @Column(name = "medical_record")
    private String medicalRecord;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "username")
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "caregiver_id")
    private Caregiver caregiver;

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    private List<MedicationPlan> medicationPlans = new ArrayList<>();


    public Patient() {
    }



    public Patient(Integer patientId, String name, String birthDate, String gender,
                   String address, String medicalRecord, Users user,
                   Caregiver caregiver) {
        this.patientId = patientId;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.medicalRecord = medicalRecord;
        this.user = user;
        this.caregiver = caregiver;
    }

    public Patient(Integer patientId, String name, String birthDate, String gender, String address, String medicalRecord,
                   Users user, Caregiver caregiver, List<MedicationPlan> medicationPlans) {
        this.patientId = patientId;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.medicalRecord = medicalRecord;
        this.user = user;
        this.caregiver = caregiver;
        this.medicationPlans = medicationPlans;
    }

    public Patient(String name, String birthDate, String gender, String address, String medicalRecord,
                   Users user, Caregiver caregiver, List<MedicationPlan> medicationPlans) {
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.medicalRecord = medicalRecord;
        this.user = user;
        this.caregiver = caregiver;
        this.medicationPlans = medicationPlans;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(String medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Caregiver getCaregiver() {
        return caregiver;
    }

    public void setCaregiver(Caregiver caregiver) {
        this.caregiver = caregiver;
    }

    public List<MedicationPlan> getMedicationPlans() {
        return medicationPlans;
    }

    public void setMedicationPlans(List<MedicationPlan> medicationPlans) {
        this.medicationPlans = medicationPlans;
    }


    @Override
    public String toString() {
        return "Patient{" +
                "patientId=" + patientId +
                ", name='" + name + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", medicalRecord='" + medicalRecord + '\'' +
                ", user=" + user +
                ", caregiver=" + caregiver +
                ", medicationPlans=" + medicationPlans +
                '}';
    }
}
