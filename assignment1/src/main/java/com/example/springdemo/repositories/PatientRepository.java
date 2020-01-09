package com.example.springdemo.repositories;

import com.example.springdemo.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

    @Query(value = "SELECT p " +
            "FROM Patient p " +
            "INNER JOIN FETCH p.medicationPlans md"
    )
    List<Patient> getAllFetch();

    @Query(value = "SELECT p " +
            "FROM Patient p " +
            "INNER JOIN FETCH p.caregiver c"
    )
    List<Patient> getAllFetch2();

}
