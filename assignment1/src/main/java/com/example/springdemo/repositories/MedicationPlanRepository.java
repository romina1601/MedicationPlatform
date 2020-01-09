package com.example.springdemo.repositories;

import com.example.springdemo.entities.MedicationPlan;
import com.example.springdemo.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MedicationPlanRepository extends JpaRepository<MedicationPlan, Integer> {

    @Query(value = "SELECT mp " +
            "FROM MedicationPlan mp " +
            "INNER JOIN FETCH mp.medications m"
    )
    List<MedicationPlan> getAllFetch();

    @Query( value =
            "SELECT DISTINCT mp " +
            "FROM MedicationPlan mp " +
            "INNER JOIN FETCH mp.medications m " +
            "WHERE mp.patient.patientId = :patientId" +
            " ORDER BY mp.medicationPlanId"
    )
    List<MedicationPlan> getAllByPatientId(@Param("patientId")Integer patientId);
}
