package com.example.springdemo.dto.builders.PatientBuilders;

import com.example.springdemo.dto.CaregiverDTOs.CaregiverForPatientDTO;
import com.example.springdemo.dto.MedicationPlanDTOs.MedicationPlanForPatientDTO;
import com.example.springdemo.dto.PatientDTOs.PatientViewDTO;
import com.example.springdemo.dto.builders.CaregiverBuilders.CaregiverForPatientBuilder;
import com.example.springdemo.dto.builders.MedicationPlanBuilders.MedicationPlanForPatientBuilder;
import com.example.springdemo.entities.MedicationPlan;
import com.example.springdemo.entities.Patient;

import java.util.List;
import java.util.stream.Collectors;

public class PatientViewBuilder {

    private PatientViewBuilder() {
    }


    public static PatientViewDTO generateDTOFromEntity(Patient patient)
    {
        List<MedicationPlan> medicationPlans = patient.getMedicationPlans();
        List<MedicationPlanForPatientDTO> medicationPlanForPatientDTOS =  medicationPlans.stream()
                .map(MedicationPlanForPatientBuilder::generateDTOFromEntity)
                .collect(Collectors.toList());


        return new PatientViewDTO(
                patient.getPatientId(),
                patient.getName(),
                patient.getBirthDate(),
                patient.getGender(),
                patient.getAddress(),
                patient.getMedicalRecord(),
                patient.getUser().getUsername(),
                CaregiverForPatientBuilder.generateDTOFromEntity(patient.getCaregiver()),
                medicationPlanForPatientDTOS
        );
    }
}
