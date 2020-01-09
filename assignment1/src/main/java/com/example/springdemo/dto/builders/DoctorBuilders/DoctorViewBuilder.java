package com.example.springdemo.dto.builders.DoctorBuilders;

import com.example.springdemo.dto.DoctorDTOs.DoctorViewDTO;
import com.example.springdemo.entities.Doctor;

public class DoctorViewBuilder {


    public DoctorViewBuilder() {
    }


    public static DoctorViewDTO generateDTOFromEntity (Doctor doctor, String username)
    {
        return new DoctorViewDTO(
                doctor.getDoctorId(),
                doctor.getName(),
                doctor.getSpecialization(),
                username
        );
    }


    public static Doctor generateEntityFromDTO(DoctorViewDTO doctorViewDTO)
    {

        return new Doctor(
                doctorViewDTO.getName(),
                doctorViewDTO.getSpecialization()
        );
    }
}
