package com.example.springdemo.services;


import com.example.springdemo.dto.PatientDTOs.JustPatientDTO;
import com.example.springdemo.dto.PatientDTOs.PatientInsertDTO;
import com.example.springdemo.dto.PatientDTOs.PatientUpdateDTO;
import com.example.springdemo.dto.PatientDTOs.PatientViewDTO;
import com.example.springdemo.dto.RecommendationDTOs.RecommendationDTO;
import com.example.springdemo.dto.UsersDTOs.UsersDTO;
import com.example.springdemo.dto.builders.PatientBuilders.JustPatientBuilder;
import com.example.springdemo.dto.builders.PatientBuilders.PatientViewBuilder;
import com.example.springdemo.dto.builders.RecommendationBuilders.RecommendationBuilder;
import com.example.springdemo.dto.builders.UsersBuilders.UsersBuilder;
import com.example.springdemo.entities.*;
import com.example.springdemo.errorhandler.DuplicateEntryException;
import com.example.springdemo.errorhandler.ResourceNotFoundException;
import com.example.springdemo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final UsersRepository usersRepository;
    private final CaregiverRepository caregiverRepository;
    private final MedicationPlanRepository medicationPlanRepository;
    private final RecommendationRepository recommendationRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository,
                          UsersRepository usersRepository,
                          CaregiverRepository caregiverRepository,
                          MedicationPlanRepository medicationPlanRepository,
                          RecommendationRepository recommendationRepository)
    {
        this.patientRepository = patientRepository;
        this.usersRepository = usersRepository;
        this.caregiverRepository = caregiverRepository;
        this.medicationPlanRepository = medicationPlanRepository;
        this.recommendationRepository = recommendationRepository;
    }





    public List<PatientViewDTO> findAll()
    {
        try
        {
            List<Patient> patients = patientRepository.findAll();
            return patients.stream()
                    .map(x -> PatientViewBuilder.generateDTOFromEntity(x))
                    .collect(Collectors.toList());
        }
        catch(NullPointerException e)
        {
            System.out.println(e.getMessage());
            throw new NullPointerException();
        }
    }

    public PatientViewDTO findPatientById (Integer patientId)
    {
        Optional<Patient> patient = patientRepository.findById(patientId);

        if(!patient.isPresent())
        {
            throw new ResourceNotFoundException("Patient", "ID", patientId);
        }

        return PatientViewBuilder.generateDTOFromEntity(patient.get());
    }


    public JustPatientDTO insert(PatientInsertDTO patientInsertDTO, UsersDTO usersDTO, Integer caregiverId) {

        Users user = usersRepository.findById(usersDTO.getUsername()).orElse(null);

        if(user != null)
        {
            throw new DuplicateEntryException("User", "username", usersDTO.getUsername());
        }

        Users userToInsert = new Users(usersDTO.getUsername(), UsersBuilder.encodePassword(usersDTO.getPassword()), usersDTO.getRole());
        userToInsert = usersRepository.save(userToInsert);

        Caregiver caregiver = caregiverRepository.findById(caregiverId).orElse(null);
        if (caregiver == null) {
            throw new ResourceNotFoundException("Caregiver", "ID", caregiverId);
        }


        Patient patient = new Patient(patientInsertDTO.getName(), patientInsertDTO.getBirthDate(),
                patientInsertDTO.getGender(), patientInsertDTO.getAddress(), patientInsertDTO.getMedicalRecord(),
                userToInsert, caregiver, null);

        Patient insertedPatient = patientRepository.save(patient);

        return JustPatientBuilder.generateDTOFromEntity2(insertedPatient);
    }


    public JustPatientDTO update (Integer patientId, PatientUpdateDTO patientUpdateDTO) throws Exception {
        Patient patient = patientRepository.findById(patientId).orElse(null);

        if(patient == null)
        {
            throw new ResourceNotFoundException("Patient", "ID", patientId);
        }

        Patient patientToUpdate = new Patient(patient.getPatientId(), patientUpdateDTO.getName(),
                patient.getBirthDate(), patientUpdateDTO.getGender(), patientUpdateDTO.getAddress(),
                patientUpdateDTO.getMedicalRecord(), patient.getUser(),
                patient.getCaregiver(), patient.getMedicationPlans());

        return JustPatientBuilder.generateDTOFromEntity2(patientRepository.save(patientToUpdate));
    }


    public void delete (Integer patientId)
    {
        Patient patient = patientRepository.findById(patientId).orElse(null);

        if (patient == null) {
            throw new ResourceNotFoundException("Patient", "ID", patientId);
        }

        List<MedicationPlan> medicationPlans = patient.getMedicationPlans();
        for(MedicationPlan medPlan : medicationPlans)
        {
            medicationPlanRepository.delete(medPlan);
        }

        Caregiver caregiver = patient.getCaregiver();
        List<Patient> patients = caregiver.getPatients();
        patients.remove(patient);

        usersRepository.deleteById(patient.getUser().getUsername());
        patientRepository.deleteById(patientId);
    }

    public PatientViewDTO findPatientByUsername (String username)
    {
        Optional<Users> user = usersRepository.findById(username);

        if(!user.isPresent())
        {
            throw new ResourceNotFoundException("User", "username", username);
        }

        List<PatientViewDTO> allPatients = this.findAll();
        for(PatientViewDTO patientViewDTO: allPatients)
        {
            if(patientViewDTO.getUsername() == username)
            {
                return patientViewDTO;
            }
        }

        return null;
    }


    public PatientViewDTO changeCaregiver (Integer patientId, Integer caregiverId) throws Exception {
        Patient patient = patientRepository.findById(patientId).orElse(null);

        if(patient == null)
        {
            throw new ResourceNotFoundException("Patient", "ID", patientId);
        }


        Caregiver newCaregiver = caregiverRepository.findById(caregiverId).orElse(null);
        if(newCaregiver == null)
        {
            throw new ResourceNotFoundException("Caregiver", "ID", patientId);
        }

        Caregiver oldCaregiver = patient.getCaregiver();
        oldCaregiver.getPatients().remove(patient);


        patient.setCaregiver(newCaregiver);
        newCaregiver.getPatients().add(patient);

        caregiverRepository.save(oldCaregiver);
        caregiverRepository.save(newCaregiver);

        return PatientViewBuilder.generateDTOFromEntity(patientRepository.save(patient));
    }

    public Integer getCaregiverIdOfPatient(Integer patientId)
    {
        Patient patient = patientRepository.findById(patientId).orElse(null);

        if(patient == null)
        {
            throw new ResourceNotFoundException("Patient", "ID", patientId);
        }

        return patient.getCaregiver().getId();
    }

    public String getCaregiverUsername(Integer patientId)
    {
        Patient patient = patientRepository.findById(patientId).orElse(null);

        if(patient == null)
        {
            throw new ResourceNotFoundException("Patient", "ID", patientId);
        }
        Caregiver newCaregiver = patient.getCaregiver();

        return newCaregiver.getUser().getUsername();
    }

    public RecommendationDTO insertRecommendation (Integer patientId, String description)
    {
        Patient patient = patientRepository.findById(patientId).orElse(null);

        if(patient == null)
        {
            throw new ResourceNotFoundException("Patient", "ID", patientId);
        }

        Recommendation recommendation = recommendationRepository.save(new Recommendation(patientId, description));
        return RecommendationBuilder.generateDTOFromEntity(recommendation);
    }

    public RecommendationDTO getRecommendationByPatientById (Integer patientId)
    {
        Optional<Patient> patient = patientRepository.findById(patientId);

        if(!patient.isPresent())
        {
            throw new ResourceNotFoundException("Patient", "ID", patientId);
        }

        Recommendation recommendation = recommendationRepository.getRecommendationByPatientId(patientId);

        return RecommendationBuilder.generateDTOFromEntity(recommendation);
    }
}
