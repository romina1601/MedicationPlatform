package com.example.springdemo.dto.DoctorDTOs;

import com.example.springdemo.dto.UsersDTOs.UsersDTOWithUsername;

import java.util.Objects;

public class DoctorViewDTO {

    private Integer doctorId;
    private String name;
    private String specialization;
    private String username;

    public DoctorViewDTO() {
    }

    public DoctorViewDTO(Integer doctorId, String name, String specialization, String username) {
        this.doctorId = doctorId;
        this.name = name;
        this.specialization = specialization;
        this.username = username;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoctorViewDTO that = (DoctorViewDTO) o;
        return Objects.equals(doctorId, that.doctorId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(specialization, that.specialization) &&
                Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doctorId, name, specialization, username);
    }
}