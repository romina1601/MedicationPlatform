package com.example.springdemo.dto.builders.CaregiverBuilders;

import com.example.springdemo.dto.CaregiverDTOs.JustCaregiverDTO;
import com.example.springdemo.entities.Caregiver;

public class JustCaregiverBuilder {

    public JustCaregiverBuilder() {
    }

    public static JustCaregiverDTO generateDTOFromEntity(Caregiver caregiver)
    {

        return new JustCaregiverDTO(
                caregiver.getId(),
                caregiver.getName(),
                caregiver.getBirthDate(),
                caregiver.getGender(),
                caregiver.getAddress(),
                caregiver.getUser().getUsername()
        );
    }
}
