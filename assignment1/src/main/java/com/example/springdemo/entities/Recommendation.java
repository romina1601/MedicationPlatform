package com.example.springdemo.entities;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "recommendation")
public class Recommendation {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "recommendation_id", unique = true, nullable = false)
    private Integer recommendationId;

    @Column(name = "patient_id")
    private Integer patientId;

    @Column(name = "description", length = 200)
    private String description;

    public Recommendation(Integer patientId, String description) {
        this.patientId = patientId;
        this.description = description;
    }

    public Integer getRecommendationId() {
        return recommendationId;
    }

    public void setRecommendationId(Integer recommendationId) {
        this.recommendationId = recommendationId;
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
        return "Recommendation{" +
                "recommendationId=" + recommendationId +
                ", patientId=" + patientId +
                ", description='" + description + '\'' +
                '}';
    }
}
