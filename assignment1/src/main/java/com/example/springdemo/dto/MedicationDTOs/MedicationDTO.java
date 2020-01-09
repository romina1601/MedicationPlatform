package com.example.springdemo.dto.MedicationDTOs;

import java.util.Objects;

public class MedicationDTO {

    private Integer medicationId;
    private String name;
    private String sideEffects;
    private String dosage;
    private String intakeInterval;


    public MedicationDTO() {
    }

    public MedicationDTO(Integer medicationId, String name, String sideEffects, String dosage,
                         String intakeInterval) {
        this.medicationId = medicationId;
        this.name = name;
        this.sideEffects = sideEffects;
        this.dosage = dosage;
        this.intakeInterval = intakeInterval;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicationDTO that = (MedicationDTO) o;
        return Objects.equals(medicationId, that.medicationId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(sideEffects, that.sideEffects) &&
                Objects.equals(dosage, that.dosage) &&
                Objects.equals(intakeInterval, that.intakeInterval);
    }

    @Override
    public int hashCode() {
        return Objects.hash(medicationId, name, sideEffects, dosage, intakeInterval);
    }
}
