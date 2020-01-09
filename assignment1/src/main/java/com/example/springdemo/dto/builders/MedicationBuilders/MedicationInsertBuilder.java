package com.example.springdemo.dto.builders.MedicationBuilders;

import com.example.springdemo.dto.MedicationDTOs.MedicationInsertDTO;
import com.example.springdemo.entities.Medication;

public class MedicationInsertBuilder {

    public static MedicationInsertDTO generateDTOFromEntity (Medication medication)
    {
        return new MedicationInsertDTO(
                medication.getName(),
                medication.getSideEffects(),
                medication.getDosage(),
                medication.getIntakeInterval()
        );
    }
}
