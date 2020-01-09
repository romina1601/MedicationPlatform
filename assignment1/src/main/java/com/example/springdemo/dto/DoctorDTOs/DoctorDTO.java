package com.example.springdemo.dto.DoctorDTOs;


import com.example.springdemo.entities.Users;

import java.util.Objects;

public class DoctorDTO {

    private Integer doctorId;
    private String name;
    private String specialization;
    private Users user;


    public DoctorDTO() {
    }

    public DoctorDTO(Integer doctorId, String name, String specialization, Users user) {
        this.doctorId = doctorId;
        this.name = name;
        this.specialization = specialization;
        this.user = user;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
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

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoctorDTO doctorDTO = (DoctorDTO) o;
        return Objects.equals(doctorId, doctorDTO.doctorId) &&
                Objects.equals(name, doctorDTO.name) &&
                Objects.equals(specialization, doctorDTO.specialization) &&
                Objects.equals(user, doctorDTO.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doctorId, name, specialization, user);
    }
}
