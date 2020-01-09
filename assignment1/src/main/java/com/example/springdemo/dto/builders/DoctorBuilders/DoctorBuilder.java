package com.example.springdemo.dto.builders.DoctorBuilders;

import com.example.springdemo.dto.DoctorDTOs.DoctorDTO;
import com.example.springdemo.entities.Doctor;
import com.example.springdemo.entities.Users;

public class DoctorBuilder {

    private DoctorBuilder() {
    }

    public static DoctorDTO generateDTOFromEntity (Doctor doctor, Users user)
    {
        return new DoctorDTO(
                doctor.getDoctorId(),
                doctor.getName(),
                doctor.getSpecialization(),
                user
        );
    }

    public static Doctor generateEntityFromDTO(DoctorDTO doctorDTO)
    {
        return new Doctor(
                doctorDTO.getDoctorId(),
                doctorDTO.getName(),
                doctorDTO.getSpecialization(),
                doctorDTO.getUser()
        );
    }
}
