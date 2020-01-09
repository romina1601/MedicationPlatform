package com.example.springdemo.dto.builders.MedicationPlanBuilders;

import com.example.springdemo.dto.MedicationPlanDTOs.MedicationPlanUpdateDTO;
import com.example.springdemo.entities.MedicationPlan;

public class MedicationPlanUpdateBuilder {

    public MedicationPlanUpdateBuilder() {
    }

    public static MedicationPlanUpdateDTO generateDTOFromEntity(MedicationPlan medicationPlan)
    {
        return new MedicationPlanUpdateDTO(
                medicationPlan.getMedicationPlanId(),
                medicationPlan.getName(),
                medicationPlan.getStartDate(),
                medicationPlan.getEndDate()
        );
    }
}
