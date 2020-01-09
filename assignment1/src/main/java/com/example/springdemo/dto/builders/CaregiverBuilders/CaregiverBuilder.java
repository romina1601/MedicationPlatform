package com.example.springdemo.dto.builders.CaregiverBuilders;

import com.example.springdemo.dto.CaregiverDTOs.CaregiverDTO;
import com.example.springdemo.entities.Caregiver;

public class CaregiverBuilder {

    private CaregiverBuilder() {
    }

    public static CaregiverDTO generateDTOFromEntity (Caregiver caregiver)
    {
        return new CaregiverDTO(
                caregiver.getId(),
                caregiver.getName(),
                caregiver.getBirthDate(),
                caregiver.getGender(),
                caregiver.getAddress(),
                caregiver.getUser()
        );
    }

    public static Caregiver generateEntityFromDTO (CaregiverDTO caregiverDTO)
    {
        return new Caregiver(
                caregiverDTO.getCaregiverId(),
                caregiverDTO.getName(),
                caregiverDTO.getBirthDate(),
                caregiverDTO.getGender(),
                caregiverDTO.getAddress(),
                caregiverDTO.getUser()
        );
    }



}
