package com.example.springdemo.services;

import com.example.springdemo.dto.MedicationPlanDTOs.MedicationPlanDTO;
import com.example.springdemo.dto.MedicationPlanDTOs.MedicationPlanForPatientDTO;
import com.example.springdemo.dto.MedicationPlanDTOs.MedicationPlanInsertDTO;
import com.example.springdemo.dto.MedicationPlanDTOs.MedicationPlanUpdateDTO;
import com.example.springdemo.dto.builders.MedicationPlanBuilders.MedicationPlanBuilder;
import com.example.springdemo.dto.builders.MedicationPlanBuilders.MedicationPlanInsertBuilder;
import com.example.springdemo.dto.builders.MedicationPlanBuilders.MedicationPlanUpdateBuilder;
import com.example.springdemo.entities.Medication;
import com.example.springdemo.entities.MedicationPlan;
import com.example.springdemo.entities.Patient;
import com.example.springdemo.entities.Users;
import com.example.springdemo.errorhandler.DuplicateEntryException;
import com.example.springdemo.errorhandler.ResourceNotFoundException;
import com.example.springdemo.repositories.MedicationPlanRepository;
import com.example.springdemo.repositories.MedicationRepository;
import com.example.springdemo.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicationPlanService {

    private final MedicationPlanRepository medicationPlanRepository;
    private final PatientRepository patientRepository;
    private final MedicationRepository medicationRepository;

    @Autowired

    public MedicationPlanService(MedicationPlanRepository medicationPlanRepository,
                                 PatientRepository patientRepository,
                                 MedicationRepository medicationRepository) {
        this.medicationPlanRepository = medicationPlanRepository;
        this.patientRepository = patientRepository;
        this.medicationRepository = medicationRepository;
    }


    public List<MedicationPlanDTO> findAllFetch(){
        List<MedicationPlan> medicationPlans = medicationPlanRepository.findAll();

        return medicationPlans.stream()
                .map(x-> MedicationPlanBuilder.generateDTOFromEntity(x, x.getPatient(), x.getMedications()))
                .collect(Collectors.toList());
    }


    public MedicationPlanDTO findMedPlanById (Integer medPlanId)
    {
        Optional<MedicationPlan> medicationPlan = medicationPlanRepository.findById(medPlanId);



        if(!medicationPlan.isPresent())
        {
            throw new ResourceNotFoundException("Medication Plan", "ID", medPlanId);
        }

        return MedicationPlanBuilder.generateDTOFromEntity(medicationPlan.get(), medicationPlan.get().getPatient(),
                medicationPlan.get().getMedications());
    }

    public MedicationPlanUpdateDTO insert(MedicationPlanInsertDTO medicationPlanInsertDTO, Integer patientId) {

        Patient patient = patientRepository.findById(patientId).orElse(null);

        if(patient == null)
        {
            throw new ResourceNotFoundException("Patient", "ID", patientId);
        }

        MedicationPlan medicationPlan = new MedicationPlan(medicationPlanInsertDTO.getName(),
                                                            medicationPlanInsertDTO.getStartDate(),
                                                            medicationPlanInsertDTO.getEndDate(),
                                                            patient, null);


        MedicationPlan insertedMedPlan = medicationPlanRepository.save(medicationPlan);

        patient.getMedicationPlans().add(insertedMedPlan);

        return MedicationPlanUpdateBuilder.generateDTOFromEntity(insertedMedPlan);
    }

    public MedicationPlanDTO updateMedPlan(Integer medPlanId, MedicationPlanUpdateDTO medicationplanUpdateDTO)
    {
        MedicationPlan medicationPlan = medicationPlanRepository.findById(medPlanId).orElse(null);

        MedicationPlan medPlanToUpdate = new MedicationPlan(medPlanId, medicationplanUpdateDTO.getName(),
                                        medicationplanUpdateDTO.getStartDate(), medicationplanUpdateDTO.getEndDate(),
                                        medicationPlan.getPatient(), medicationPlan.getMedications());

        return MedicationPlanBuilder.generateDTOFromEntity(medicationPlanRepository.save(medPlanToUpdate), medPlanToUpdate.getPatient(),
                                                            medPlanToUpdate.getMedications());
    }


    public void deleteMedPlan (Integer medPlanId) throws Exception
    {
        MedicationPlan medicationPlan = medicationPlanRepository.findById(medPlanId).orElse(null);

        if (medicationPlan == null) {
            throw new ResourceNotFoundException("Medication Plan", "ID", medPlanId);
        }

        List<Medication> medications = medicationPlan.getMedications();
        for(Medication medication : medications)
        {
            medication.getMedicationPlans().remove(medicationPlan);
        }

        Patient patient = medicationPlan.getPatient();
        patient.getMedicationPlans().remove(medicationPlan);
        medicationPlanRepository.deleteById(medPlanId);
    }


    /*********** ASSIGNMENT 3 ************/



}
