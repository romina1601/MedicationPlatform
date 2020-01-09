package com.example.springdemo.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "medication")
public class Medication {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "medication_id", unique = true, nullable = false)
    private Integer medicationId;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "side_effects", nullable = false, length = 200)
    private String sideEffects;

    @Column(name = "dosage")
    private String dosage;

    @Column(name = "intake_interval")
    private String intakeInterval;

    @ManyToMany(mappedBy = "medications")
    private List<MedicationPlan> medicationPlans = new ArrayList<>();

    public Medication() {
    }

    public Medication(Integer medicationId, String name, String sideEffects, String dosage, String intakeInterval) {
        this.medicationId = medicationId;
        this.name = name;
        this.sideEffects = sideEffects;
        this.dosage = dosage;
        this.intakeInterval = intakeInterval;
    }

    public Medication(String name, String sideEffects, String dosage,
                      String intakeInterval, List<MedicationPlan> medicationPlans) {
        this.name = name;
        this.sideEffects = sideEffects;
        this.dosage = dosage;
        this.intakeInterval = intakeInterval;
        this.medicationPlans = medicationPlans;
    }

    public Integer getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(Integer medicationId) {
        this.medicationId = medicationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSideEffects() {
        return sideEffects;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getIntakeInterval() {
        return intakeInterval;
    }

    public void setIntakeInterval(String intakeInterval) {
        this.intakeInterval = intakeInterval;
    }

    public List<MedicationPlan> getMedicationPlans() {
        return medicationPlans;
    }

    public void setMedicationPlans(List<MedicationPlan> medicationPlans) {
        this.medicationPlans = medicationPlans;
    }
}
