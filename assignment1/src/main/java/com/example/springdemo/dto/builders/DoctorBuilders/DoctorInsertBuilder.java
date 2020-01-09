package com.example.springdemo.dto.builders.DoctorBuilders;

import com.example.springdemo.dto.DoctorDTOs.DoctorInsertDTO;
import com.example.springdemo.entities.Doctor;

/**
 * Not used anymore
 * As of 21.10.2019
 */

public class DoctorInsertBuilder {

    public static DoctorInsertDTO generateDTOFromEntity (Doctor doctor, String username)
    {
        return new DoctorInsertDTO(
                doctor.getName(),
                doctor.getSpecialization(),
                username
        );
    }
}
