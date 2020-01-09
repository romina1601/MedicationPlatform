package com.example.springdemo.repositories;

import com.example.springdemo.entities.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RecommendationRepository extends JpaRepository<Recommendation, Integer> {

    @Query( value =
                    "SELECT r " +
                    "FROM Recommendation r " +
                    "WHERE r.patientId = :patientId"
    )
    Recommendation getRecommendationByPatientId(@Param("patientId")Integer patientId);
}
