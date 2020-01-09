package com.example.springdemo.dto.RecommendationDTOs;

import java.util.Objects;

public class RecommendationDTO {


    private Integer patientId;
    private String description;

    public RecommendationDTO(Integer patientId, String description) {

        this.patientId = patientId;
        this.description = description;
    }


    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "RecommendationDTO{" +
                ", patientId=" + patientId +
                ", description='" + description + '\'' +
                '}';
    }
}
