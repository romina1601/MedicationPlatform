package com.example.springdemo.services;

import com.example.springdemo.dto.MedicationDTOs.MedicationDTO;
import com.example.springdemo.dto.MedicationDTOs.MedicationInsertDTO;
import com.example.springdemo.dto.MedicationDTOs.MedicationWithMedPlanDTO;
import com.example.springdemo.dto.MedicationPlanDTOs.MedicationPlanDTO;
import com.example.springdemo.dto.builders.MedicationBuilders.MedicationBuilder;
import com.example.springdemo.dto.builders.MedicationPlanBuilders.MedicationPlanBuilder;
import com.example.springdemo.entities.Medication;
import com.example.springdemo.entities.MedicationPlan;
import com.example.springdemo.errorhandler.ResourceNotFoundException;
import com.example.springdemo.repositories.MedicationPlanRepository;
import com.example.springdemo.repositories.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicationService {

    private final MedicationRepository medicationRepository;
    private final MedicationPlanRepository medicationPlanRepository;

    @Autowired
    public MedicationService(MedicationRepository medicationRepository,
                             MedicationPlanRepository medicationPlanRepository) {
        this.medicationRepository = medicationRepository;
        this.medicationPlanRepository = medicationPlanRepository;
    }

    public List<MedicationDTO> findAll()
    {
        List<Medication> medications = medicationRepository.findAll();
        return medications.stream()
                .map(MedicationBuilder::generateDTOFromEntity)
                .collect(Collectors.toList());
    }

    public MedicationDTO findMedicationById (Integer  medicationId)
    {
        Medication medication = medicationRepository.findById(medicationId).orElse(null);

        if(medication == null)
        {
            throw new ResourceNotFoundException("Medication", "ID", medicationId);
        }

        return MedicationBuilder.generateDTOFromEntity(medication);
    }


    public MedicationDTO insert(MedicationInsertDTO medicationInsertDTO) {


        Medication medicationInserted = new Medication(medicationInsertDTO.getName(),
                medicationInsertDTO.getSideEffects(), medicationInsertDTO.getDosage(),
                medicationInsertDTO.getIntakeInterval(), null);

        return MedicationBuilder.generateDTOFromEntity(medicationRepository.save(medicationInserted));
    }


    public MedicationDTO update (Integer medicationId, MedicationInsertDTO medicationInsertDTO) throws Exception {

        Medication medication = medicationRepository.findById(medicationId).orElse(null);

        if(medication == null)
        {
            throw new ResourceNotFoundException("Medication", "ID", medicationId);
        }


        Medication medicationToUpdate = new Medication(medicationId, medicationInsertDTO.getName(),
                medicationInsertDTO.getSideEffects(), medicationInsertDTO.getDosage(),
                medicationInsertDTO.getIntakeInterval());

        return MedicationBuilder.generateDTOFromEntity(medicationRepository.save(medicationToUpdate));
    }


    public void delete (Integer medicationId) throws Exception
    {
        Medication medication = medicationRepository.findById(medicationId).orElse(null);
        if (medication == null) {
            throw new ResourceNotFoundException("Medication", "ID", medicationId);
        }

        List<MedicationPlan> medicationPlans = medication.getMedicationPlans();
        for(MedicationPlan medicationPlan : medicationPlans)
        {
            medicationPlan.getMedications().remove(medication);
        }
        medicationRepository.deleteById(medicationId);
    }

    public MedicationDTO addMedicationToMedPlan(Integer medicationId, Integer medPlanId)
    {
        MedicationPlan medicationPlan = medicationPlanRepository.findById(medPlanId).orElse(null);
        if (medicationPlan == null) {
            throw new ResourceNotFoundException("Medication Plan", "ID", medPlanId);
        }

        Medication medication = medicationRepository.findById(medicationId).orElse(null);
        if (medication == null) {
            throw new ResourceNotFoundException("Medication", "ID", medPlanId);
        }

        medicationPlan.getMedications().add(medication);
        medication.getMedicationPlans().add(medicationPlan);

        medicationPlanRepository.save(medicationPlan);

        return MedicationBuilder.generateDTOFromEntity(medicationRepository.save(medication));
    }
}
