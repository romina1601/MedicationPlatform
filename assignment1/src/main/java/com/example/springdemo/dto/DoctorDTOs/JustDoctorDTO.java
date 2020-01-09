package com.example.springdemo.dto.DoctorDTOs;

import java.util.Objects;

public class JustDoctorDTO {

    private String name;
    private String specialization;

    public JustDoctorDTO() {
    }

    public JustDoctorDTO(String name, String specialization) {
        this.name = name;
        this.specialization = specialization;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JustDoctorDTO that = (JustDoctorDTO) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(specialization, that.specialization);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, specialization);
    }
}
