package com.example.springdemo.dto.builders.MedicationPlanBuilders;

import com.example.springdemo.dto.MedicationPlanDTOs.MedicationPlanInsertDTO;
import com.example.springdemo.dto.PatientDTOs.JustPatientDTO;
import com.example.springdemo.dto.builders.PatientBuilders.JustPatientBuilder;
import com.example.springdemo.entities.MedicationPlan;

public class MedicationPlanInsertBuilder {

    public MedicationPlanInsertBuilder() {
    }


    public static MedicationPlanInsertDTO generateDTOFromEntity(MedicationPlan medicationPlan)
    {
        return new MedicationPlanInsertDTO(
                medicationPlan.getName(),
                medicationPlan.getStartDate(),
                medicationPlan.getEndDate()
        );
    }
}
