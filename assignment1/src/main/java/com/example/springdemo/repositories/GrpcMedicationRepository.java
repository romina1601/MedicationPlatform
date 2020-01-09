package com.example.springdemo.repositories;

import com.example.springdemo.entities.GrpcMedication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrpcMedicationRepository extends JpaRepository<GrpcMedication, Integer> {
}
