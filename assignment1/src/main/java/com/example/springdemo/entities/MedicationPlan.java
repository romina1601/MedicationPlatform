package com.example.springdemo.entities;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "medication_plan")
public class MedicationPlan {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "medication_plan_id", unique = true, nullable = false)
    private Integer medicationPlanId;

    @Column(name = "name")
    private String name;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "end_date")
    private String endDate;

    @ManyToOne()
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToMany (cascade = CascadeType.ALL)
    @JoinTable(
            name = "medication_plan_medication",
            joinColumns = @JoinColumn(name = "medication_plan_id"),
            inverseJoinColumns = @JoinColumn(name = "medication_id")
    )
    private List<Medication> medications = new ArrayList<>();

    public MedicationPlan() {
    }


    public MedicationPlan(Integer medicationPlanId, String name, String startDate, String endDate,
                          Patient patient, List<Medication> medications) {
        this.medicationPlanId = medicationPlanId;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.patient = patient;
        this.medications = medications;
    }

    public MedicationPlan(String name, String startDate, String endDate,
                          Patient patient, List<Medication> medications) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.patient = patient;
        this.medications = medications;
    }

    public Integer getMedicationPlanId() {
        return medicationPlanId;
    }

    public void setMedicationPlanId(Integer medicationPlanId) {
        this.medicationPlanId = medicationPlanId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<Medication> getMedications() {
        return medications;
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }

    @Override
    public String toString() {
        return "MedicationPlan{" +
                "medicationPlanId=" + medicationPlanId +
                ", name='" + name + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", patient=" + patient +
                ", medications=" + medications +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicationPlan that = (MedicationPlan) o;
        return Objects.equals(medicationPlanId, that.medicationPlanId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(patient, that.patient) &&
                Objects.equals(medications, that.medications);
    }

    @Override
    public int hashCode() {
        return Objects.hash(medicationPlanId, name, startDate, endDate, patient, medications);
    }
}
