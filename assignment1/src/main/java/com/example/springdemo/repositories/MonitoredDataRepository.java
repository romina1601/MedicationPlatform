package com.example.springdemo.repositories;

import com.example.springdemo.entities.MonitoredData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonitoredDataRepository extends JpaRepository<MonitoredData, Integer> {
}
