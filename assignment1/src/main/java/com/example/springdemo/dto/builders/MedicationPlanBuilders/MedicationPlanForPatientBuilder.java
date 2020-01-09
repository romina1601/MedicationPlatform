package com.example.springdemo.dto.builders.MedicationPlanBuilders;

import com.example.springdemo.dto.MedicationDTOs.MedicationDTO;
import com.example.springdemo.dto.MedicationPlanDTOs.MedicationPlanForPatientDTO;
import com.example.springdemo.dto.builders.MedicationBuilders.MedicationBuilder;
import com.example.springdemo.entities.Medication;
import com.example.springdemo.entities.MedicationPlan;

import java.util.List;
import java.util.stream.Collectors;

public class MedicationPlanForPatientBuilder {

    public MedicationPlanForPatientBuilder() {
    }


    public static MedicationPlanForPatientDTO generateDTOFromEntity(MedicationPlan medicationPlan)
    {
        List<Medication> medications = medicationPlan.getMedications();
        List<MedicationDTO> medicationDTOs =  medications.stream()
                .map(MedicationBuilder::generateDTOFromEntity)
                .collect(Collectors.toList());

        return new MedicationPlanForPatientDTO(
                medicationPlan.getMedicationPlanId(),
                medicationPlan.getName(),
                medicationPlan.getStartDate(),
                medicationPlan.getEndDate(),
                medicationDTOs
        );
    }
}
