package com.example.springdemo.repositories;

import com.example.springdemo.entities.Caregiver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CaregiverRepository extends JpaRepository<Caregiver, Integer> {

    @Query(value = "SELECT c " +
            "FROM Caregiver c " +
            "INNER JOIN FETCH c.patients p"
    )
    List<Caregiver> getAllFetch();
}
