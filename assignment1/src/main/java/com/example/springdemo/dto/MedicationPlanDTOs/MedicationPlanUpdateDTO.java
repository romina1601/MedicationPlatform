package com.example.springdemo.dto.MedicationPlanDTOs;

import java.util.Objects;

public class MedicationPlanUpdateDTO {

    private Integer medicationplanId;
    private String name;
    private String startDate;
    private String endDate;

    public MedicationPlanUpdateDTO() {
    }

    public MedicationPlanUpdateDTO(Integer medicationplanId, String name, String startDate, String endDate) {
        this.medicationplanId = medicationplanId;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Integer getMedicationplanId() {
        return medicationplanId;
    }

    public void setMedicationplanId(Integer medicationplanId) {
        this.medicationplanId = medicationplanId;
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
        MedicationPlanUpdateDTO that = (MedicationPlanUpdateDTO) o;
        return  Objects.equals(medicationplanId, that.medicationplanId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(medicationplanId, name, startDate, endDate);
    }
}
