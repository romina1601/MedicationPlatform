package com.example.springdemo.dto.MedicationPlanDTOs;

import com.example.springdemo.dto.MedicationDTOs.MedicationDTO;

import java.util.List;
import java.util.Objects;

public class MedicationPlanForPatientDTO {

    private Integer medicationPlanId;
    private String name;
    private String startDate;
    private String endDate;
    private List<MedicationDTO> medicationDTOs;

    public MedicationPlanForPatientDTO() {
    }

    public MedicationPlanForPatientDTO(Integer medicationPlanId, String name,
                                       String startDate, String endDate, List<MedicationDTO> medicationDTOs) {
        this.medicationPlanId = medicationPlanId;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.medicationDTOs = medicationDTOs;
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

    public List<MedicationDTO> getMedicationDTOs() {
        return medicationDTOs;
    }

    public void setMedicationDTOs(List<MedicationDTO> medicationDTOs) {
        this.medicationDTOs = medicationDTOs;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicationPlanForPatientDTO that = (MedicationPlanForPatientDTO) o;
        return Objects.equals(medicationPlanId, that.medicationPlanId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(medicationDTOs, that.medicationDTOs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(medicationPlanId, name, startDate, endDate, medicationDTOs);
    }
}
