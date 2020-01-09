package com.example.springdemo.repositories;

import com.example.springdemo.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    @Query(value = "SELECT p " +
            "FROM Doctor p " +
            "INNER JOIN FETCH p.user i"
    )
    List<Doctor> getAllFetch();
}