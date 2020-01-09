package com.example.springdemo.dto.builders.PatientBuilders;

import com.example.springdemo.dto.PatientDTOs.PatientInsertDTO;
import com.example.springdemo.entities.Patient;

public class PatientInsertBuilder {

    public PatientInsertBuilder() {
    }


    public static PatientInsertDTO generateDTOFromEntity(Patient patient)
    {
        return new PatientInsertDTO(
                patient.getName(),
                patient.getBirthDate(),
                patient.getGender(),
                patient.getAddress(),
                patient.getMedicalRecord()
        );
    }
}
