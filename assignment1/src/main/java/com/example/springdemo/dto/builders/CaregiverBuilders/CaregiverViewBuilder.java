package com.example.springdemo.dto.builders.CaregiverBuilders;

import com.example.springdemo.dto.CaregiverDTOs.CaregiverViewDTO;
import com.example.springdemo.dto.PatientDTOs.JustPatientDTO;
import com.example.springdemo.dto.builders.PatientBuilders.JustPatientBuilder;
import com.example.springdemo.entities.Caregiver;
import com.example.springdemo.entities.Patient;

import java.util.List;
import java.util.stream.Collectors;

public class CaregiverViewBuilder {

    private CaregiverViewBuilder() {
    }

    public static CaregiverViewDTO generateDTOFromEntity(Caregiver caregiver, String username,
                                                         List<Patient> patients)
    {

        List<JustPatientDTO> patientDTOs =  patients.stream()
                .map(JustPatientBuilder::generateDTOFromEntity2)
                .collect(Collectors.toList());

        return new CaregiverViewDTO(
                caregiver.getId(),
                caregiver.getName(),
                caregiver.getBirthDate(),
                caregiver.getGender(),
                caregiver.getAddress(),
                username,
                patientDTOs
        );
    }
}
