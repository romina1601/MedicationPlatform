package com.example.springdemo.dto.builders.CaregiverBuilders;

import com.example.springdemo.dto.CaregiverDTOs.CaregiverForPatientDTO;
import com.example.springdemo.entities.Caregiver;

public class CaregiverForPatientBuilder {

    public CaregiverForPatientBuilder() {
    }

    public static CaregiverForPatientDTO generateDTOFromEntity(Caregiver caregiver)
    {
        return new CaregiverForPatientDTO(
                caregiver.getId(),
                caregiver.getName()
        );
    }
}
