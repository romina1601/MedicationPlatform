package com.example.springdemo.dto.builders.PatientBuilders;

import com.example.springdemo.dto.MedicationPlanDTOs.MedicationPlanForPatientDTO;
import com.example.springdemo.dto.PatientDTOs.JustPatientDTO;
import com.example.springdemo.dto.builders.MedicationPlanBuilders.MedicationPlanForPatientBuilder;
import com.example.springdemo.entities.MedicationPlan;
import com.example.springdemo.entities.Patient;

import java.util.List;
import java.util.stream.Collectors;

public class JustPatientBuilder {

    public JustPatientBuilder() {
    }

    public static JustPatientDTO generateDTOFromEntity(Patient patient, String username)
    {
        List<MedicationPlan> medicationPlans = patient.getMedicationPlans();
        List<MedicationPlanForPatientDTO> medicationPlanForPatientDTOS =  medicationPlans.stream()
                .map(MedicationPlanForPatientBuilder::generateDTOFromEntity)
                .collect(Collectors.toList());

        return new JustPatientDTO(
                patient.getPatientId(),
                patient.getName(),
                patient.getBirthDate(),
                patient.getGender(),
                patient.getAddress(),
                patient.getMedicalRecord(),
                username,
                medicationPlanForPatientDTOS
        );
    }

    public static JustPatientDTO generateDTOFromEntity2(Patient patient)
    {

        List<MedicationPlan> medicationPlans = patient.getMedicationPlans();
        List<MedicationPlanForPatientDTO> medicationPlanForPatientDTOS =  medicationPlans.stream()
                .map(MedicationPlanForPatientBuilder::generateDTOFromEntity)
                .collect(Collectors.toList());

        return new JustPatientDTO(
                patient.getPatientId(),
                patient.getName(),
                patient.getBirthDate(),
                patient.getGender(),
                patient.getAddress(),
                patient.getMedicalRecord(),
                patient.getUser().getUsername(),
                medicationPlanForPatientDTOS
        );
    }

    public static List<JustPatientDTO> generateDTOFromListEntity (List<Patient> patients)
    {
        List<JustPatientDTO> patientDTOs =  patients.stream()
                .map(JustPatientBuilder::generateDTOFromEntity2)
                .collect(Collectors.toList());

        return patientDTOs;
    }
}
