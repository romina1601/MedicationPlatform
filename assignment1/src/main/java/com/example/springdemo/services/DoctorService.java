package com.example.springdemo.services;

import com.example.springdemo.dto.DoctorDTOs.DoctorDTO;
import com.example.springdemo.dto.DoctorDTOs.DoctorViewDTO;
import com.example.springdemo.dto.DoctorDTOs.JustDoctorDTO;
import com.example.springdemo.dto.UsersDTOs.UsersDTO;
import com.example.springdemo.dto.builders.DoctorBuilders.DoctorBuilder;
import com.example.springdemo.dto.builders.DoctorBuilders.DoctorViewBuilder;
import com.example.springdemo.dto.builders.UsersBuilders.UsersBuilder;
import com.example.springdemo.entities.Doctor;
import com.example.springdemo.entities.Users;
import com.example.springdemo.errorhandler.DuplicateEntryException;
import com.example.springdemo.errorhandler.ResourceNotFoundException;
import com.example.springdemo.repositories.DoctorRepository;
import com.example.springdemo.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final UsersRepository usersRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository, UsersRepository usersRepository) {
        this.doctorRepository = doctorRepository;
        this.usersRepository = usersRepository;
    }

    public List<DoctorViewDTO> findAll()
    {
        List<Doctor>doctors = doctorRepository.getAllFetch();
        return doctors.stream()
                .map(x -> DoctorViewBuilder.generateDTOFromEntity(x, x.getUser().getUsername()))
                .collect(Collectors.toList());
    }


    public DoctorViewDTO findDoctorById (Integer doctorId)
    {
        Optional<Doctor> doctor = doctorRepository.findById(doctorId);



        if(!doctor.isPresent())
        {
            throw new ResourceNotFoundException("Doctor", "ID", doctorId);
        }

        return DoctorViewBuilder.generateDTOFromEntity(doctor.get(), doctor.get().getUser().getUsername());
    }


    public DoctorDTO insert(JustDoctorDTO justDoctorDTO, UsersDTO usersDTO) {

        Users user = usersRepository.findById(usersDTO.getUsername()).orElse(null);

        if(user != null)
        {
            throw new DuplicateEntryException("User", "username", usersDTO.getUsername());
        }

        Users userToInsert = new Users(usersDTO.getUsername(), UsersBuilder.encodePassword(usersDTO.getPassword()) , usersDTO.getRole());
        userToInsert = usersRepository.save(userToInsert);


        Doctor doctor = new Doctor(justDoctorDTO.getName(), justDoctorDTO.getSpecialization(), userToInsert);
        Doctor insertedDoctor = doctorRepository.save(doctor);

        return DoctorBuilder.generateDTOFromEntity(insertedDoctor, insertedDoctor.getUser());
    }


    public DoctorViewDTO update (Integer doctorId, DoctorViewDTO doctorViewDTO) throws Exception {
        Doctor doctor = doctorRepository.findById(doctorId).orElse(null);


        if(doctor == null)
        {
            throw new ResourceNotFoundException("Doctor", "ID", doctorId);
        }
        if(!doctorId.equals(doctorViewDTO.getDoctorId()))
        {
            throw new Exception("Cannot change doctor ID");
        }
        if(!doctor.getUser().getUsername().equals(doctorViewDTO.getUsername()))
        {
            throw new Exception("Cannot change doctor's username");
        }

        Doctor doctorToUpdate = new Doctor(doctorId, doctorViewDTO.getName(), doctorViewDTO.getSpecialization(), doctor.getUser());

        return DoctorViewBuilder.generateDTOFromEntity(doctorRepository.save(doctorToUpdate), doctorToUpdate.getUser().getUsername());
    }

    public void delete (Integer doctorId) throws Exception
    {
        Doctor doctor = doctorRepository.findById(doctorId).orElse(null);

        if (doctor == null) {
            throw new ResourceNotFoundException("Doctor", "ID", doctorId);
        }

        usersRepository.deleteById(doctor.getUser().getUsername());
        doctorRepository.deleteById(doctorId);
    }


}
