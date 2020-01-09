package com.example.springdemo.dto.MedicationDTOs;

import com.example.springdemo.dto.MedicationPlanDTOs.MedicationPlanDTO;

import java.util.List;
import java.util.Objects;

public class MedicationWithMedPlanDTO {

    private Integer medicationId;
    private String name;
    private String sideEffects;
    private String dosage;
    private String intakeInterval;
    private List<MedicationPlanDTO> medicationPlanDTOs;

    public MedicationWithMedPlanDTO() {
    }

    public MedicationWithMedPlanDTO(Integer medicationId, String name, String sideEffects,
                                    String dosage, String intakeInterval,
                                    List<MedicationPlanDTO> medicationPlanDTOs) {
        this.medicationId = medicationId;
        this.name = name;
        this.sideEffects = sideEffects;
        this.dosage = dosage;
        this.intakeInterval = intakeInterval;
        this.medicationPlanDTOs = medicationPlanDTOs;
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

    public List<MedicationPlanDTO> getMedicationPlanDTOs() {
        return medicationPlanDTOs;
    }

    public void setMedicationPlanDTOs(List<MedicationPlanDTO> medicationPlanDTOs) {
        this.medicationPlanDTOs = medicationPlanDTOs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicationWithMedPlanDTO that = (MedicationWithMedPlanDTO) o;
        return Objects.equals(medicationId, that.medicationId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(sideEffects, that.sideEffects) &&
                Objects.equals(dosage, that.dosage) &&
                Objects.equals(intakeInterval, that.intakeInterval) &&
                Objects.equals(medicationPlanDTOs, that.medicationPlanDTOs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(medicationId, name, sideEffects, dosage, intakeInterval, medicationPlanDTOs);
    }
}
