package com.example.springdemo.services;

import com.example.springdemo.dto.CaregiverDTOs.*;
import com.example.springdemo.dto.UsersDTOs.UsersDTO;
import com.example.springdemo.dto.builders.CaregiverBuilders.CaregiverViewBuilder;
import com.example.springdemo.dto.builders.CaregiverBuilders.JustCaregiverBuilder;
import com.example.springdemo.dto.builders.UsersBuilders.UsersBuilder;
import com.example.springdemo.entities.Caregiver;
import com.example.springdemo.entities.Patient;
import com.example.springdemo.entities.Users;
import com.example.springdemo.errorhandler.DuplicateEntryException;
import com.example.springdemo.errorhandler.ResourceNotFoundException;
import com.example.springdemo.repositories.CaregiverRepository;
import com.example.springdemo.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CaregiverService {

    private final CaregiverRepository caregiverRepository;
    private final UsersRepository usersRepository;

    @Autowired
    public CaregiverService(CaregiverRepository caregiverRepository, UsersRepository usersRepository) {
        this.caregiverRepository = caregiverRepository;
        this.usersRepository = usersRepository;
    }

    public List<CaregiverViewDTO>findAll()
    {
        List<Caregiver> caregivers = caregiverRepository.findAll();

        return caregivers.stream()
                .map(x -> CaregiverViewBuilder.generateDTOFromEntity(x, x.getUser().getUsername(), x.getPatients()))
                .collect(Collectors.toList());
    }


    public CaregiverViewDTO findCaregiverById (Integer caregiverId)
    {
        Optional<Caregiver> caregiver = caregiverRepository.findById(caregiverId);



        if(!caregiver.isPresent())
        {
            throw new ResourceNotFoundException("Caregiver", "ID", caregiverId);
        }

        return CaregiverViewBuilder.generateDTOFromEntity(caregiver.get(), caregiver.get().getUser().getUsername(),
                caregiver.get().getPatients());
    }


    public JustCaregiverDTO insert(CaregiverInsertDTO caregiverInsertDTO, UsersDTO usersDTO) {

        Users user = usersRepository.findById(usersDTO.getUsername()).orElse(null);

        if(user != null)
        {
            throw new DuplicateEntryException("User", "username", usersDTO.getUsername());
        }

        Users userToInsert = new Users(usersDTO.getUsername(), UsersBuilder.encodePassword(usersDTO.getPassword()), usersDTO.getRole());
        userToInsert = usersRepository.save(userToInsert);


        Caregiver caregiver = new Caregiver(caregiverInsertDTO.getName(), caregiverInsertDTO.getBirthDate(),
                caregiverInsertDTO.getGender(), caregiverInsertDTO.getAddress(), userToInsert,
                null);
        Caregiver insertedCaregiver = caregiverRepository.save(caregiver);

        /*return CaregiverViewBuilder.generateDTOFromEntity(insertedCaregiver,
                userToInsert.getUsername(), insertedCaregiver.getPatients());*/
        return JustCaregiverBuilder.generateDTOFromEntity(insertedCaregiver);
    }

    public CaregiverViewDTO update (Integer caregiverId, CaregiverUpdaateDTO caregiverUpdaateDTO) throws Exception {
        Caregiver caregiver = caregiverRepository.findById(caregiverId).orElse(null);

        if(caregiver == null)
        {
            throw new ResourceNotFoundException("Caregiver", "ID", caregiverId);
        }

        Caregiver caregiverToUpdate = new Caregiver(caregiver.getId(), caregiverUpdaateDTO.getName(), caregiver.getBirthDate(),
                caregiverUpdaateDTO.getGender(), caregiverUpdaateDTO.getAddress(), caregiver.getUser(),
                caregiver.getPatients());

        return CaregiverViewBuilder.generateDTOFromEntity(caregiverRepository.save(caregiverToUpdate),
                caregiverToUpdate.getUser().getUsername(), caregiverToUpdate.getPatients());
    }


    public void delete (Integer caregiverId) throws Exception
    {
        Caregiver caregiver = caregiverRepository.findById(caregiverId).orElse(null);

        if (caregiver == null) {
            throw new ResourceNotFoundException("Caregiver", "ID", caregiverId);
        }

        List<Patient> patients = caregiver.getPatients();

        for(Patient p: patients)
        {
            p.setCaregiver(null);
        }

        usersRepository.deleteById(caregiver.getUser().getUsername());
        caregiverRepository.deleteById(caregiverId);
    }

    public CaregiverViewDTO findCaregiverByUsername (String username)
    {
        Optional<Users> user = usersRepository.findById(username);

        if(!user.isPresent())
        {
            throw new ResourceNotFoundException("User", "username", username);
        }

        List<CaregiverViewDTO> allCaregivers = this.findAll();
        for(CaregiverViewDTO caregiverViewDTO: allCaregivers)
        {
            if(caregiverViewDTO.getUsername() == username)
            {
                return caregiverViewDTO;
            }
        }

        return null;
    }

    public String getUsernameFromId(Integer caregiverId)
    {
        Caregiver caregiver = caregiverRepository.findById(caregiverId).orElse(null);

        if (caregiver == null) {
            throw new ResourceNotFoundException("Caregiver", "ID", caregiverId);
        }

        return caregiver.getUser().getUsername();

    }
}
