package com.example.springdemo.dto.builders.MedicationBuilders;

import com.example.springdemo.dto.MedicationDTOs.MedicationDTO;
import com.example.springdemo.entities.Medication;

public class MedicationBuilder {

    private MedicationBuilder() {
    }

    public static MedicationDTO generateDTOFromEntity (Medication medication)
    {
        return new MedicationDTO(
                medication.getMedicationId(),
                medication.getName(),
                medication.getSideEffects(),
                medication.getDosage(),
                medication.getIntakeInterval()
        );
    }

    public static Medication generateEntityFromDTO(MedicationDTO medicationDTO)
    {
        return new Medication(
                medicationDTO.getMedicationId(),
                medicationDTO.getName(),
                medicationDTO.getSideEffects(),
                medicationDTO.getDosage(),
                medicationDTO.getIntakeInterval()
        );
    }
}
