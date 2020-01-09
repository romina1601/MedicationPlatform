package com.example.springdemo.services;

import com.example.springdemo.entities.MonitoredData;
import com.example.springdemo.repositories.MonitoredDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonitoredDataService {

    private final MonitoredDataRepository monitoredDataRepository;

    @Autowired

    public MonitoredDataService(MonitoredDataRepository monitoredDataRepository) {
        this.monitoredDataRepository = monitoredDataRepository;
    }

    public void insert (MonitoredData monitoredData)
    {
        this.monitoredDataRepository.save(monitoredData);
    }



}
