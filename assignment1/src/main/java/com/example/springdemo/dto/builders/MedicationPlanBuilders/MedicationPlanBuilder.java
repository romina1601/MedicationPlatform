package com.example.springdemo.dto.builders.MedicationPlanBuilders;

import com.example.springdemo.dto.MedicationDTOs.MedicationDTO;
import com.example.springdemo.dto.MedicationPlanDTOs.MedicationPlanDTO;
import com.example.springdemo.dto.builders.MedicationBuilders.MedicationBuilder;
import com.example.springdemo.dto.builders.PatientBuilders.JustPatientBuilder;
import com.example.springdemo.entities.Medication;
import com.example.springdemo.entities.MedicationPlan;
import com.example.springdemo.entities.Patient;

import java.util.List;
import java.util.stream.Collectors;

public class MedicationPlanBuilder {

    public MedicationPlanBuilder() {
    }

    public static MedicationPlanDTO generateDTOFromEntity(MedicationPlan medicationPlan, Patient patient,
                                                            List<Medication> medications)
    {
        List<MedicationDTO> medicationDTOs =  medications.stream()
                .map(MedicationBuilder::generateDTOFromEntity)
                .collect(Collectors.toList());

        return new MedicationPlanDTO(
                medicationPlan.getMedicationPlanId(),
                medicationPlan.getName(),
                medicationPlan.getStartDate(),
                medicationPlan.getEndDate(),
                JustPatientBuilder.generateDTOFromEntity2(patient),
                medicationDTOs
        );
    }
}
