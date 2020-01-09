package com.example.springdemo.dto.builders.PatientBuilders;

import com.example.springdemo.dto.PatientDTOs.PatientDTO;
import com.example.springdemo.dto.builders.CaregiverBuilders.CaregiverBuilder;
import com.example.springdemo.dto.builders.UsersBuilders.UsersBuilder;
import com.example.springdemo.entities.Patient;

public class PatientBuilder {

    private PatientBuilder() {
    }

    public static PatientDTO generateDTOFromEntity(Patient patient)
    {
        return new PatientDTO(
                patient.getPatientId(),
                patient.getName(),
                patient.getBirthDate(),
                patient.getGender(),
                patient.getAddress(),
                patient.getMedicalRecord(),
                UsersBuilder.generateDTOFromEntity(patient.getUser()),
                CaregiverBuilder.generateDTOFromEntity(patient.getCaregiver())
        );
    }

    public static Patient generateEntityFromPatientDTO(PatientDTO patientDTO)
    {
        return new Patient (
                patientDTO.getPatientId(),
                patientDTO.getName(),
                patientDTO.getBirthDate(),
                patientDTO.getGender(),
                patientDTO.getAddress(),
                patientDTO.getMedicalRecord(),
                UsersBuilder.generateEntityFromDTO(patientDTO.getUsersDTO()) ,
                CaregiverBuilder.generateEntityFromDTO(patientDTO.getCaregiverDTO())
        );
    }
}
