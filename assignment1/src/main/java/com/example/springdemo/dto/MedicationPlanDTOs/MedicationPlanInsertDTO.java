package com.example.springdemo.dto.MedicationPlanDTOs;

import com.example.springdemo.dto.PatientDTOs.JustPatientDTO;

import java.util.Objects;

public class MedicationPlanInsertDTO {

    private String name;
    private String startDate;
    private String endDate;

    public MedicationPlanInsertDTO() {
    }

    public MedicationPlanInsertDTO(String name, String startDate, String endDate ) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicationPlanInsertDTO that = (MedicationPlanInsertDTO) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, startDate, endDate);
    }
}
