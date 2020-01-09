package com.example.springdemo.dto.DoctorDTOs;

import java.util.Objects;

/**
 * Not used anymore
 * As of 21.10.2019
 */

public class DoctorInsertDTO {

    private String name;
    private String specialization;
    private String username;

    public DoctorInsertDTO() {
    }

    public DoctorInsertDTO(String name, String specialization, String username) {
        this.name = name;
        this.specialization = specialization;
        this.username = username;
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
        DoctorInsertDTO that = (DoctorInsertDTO) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(specialization, that.specialization) &&
                Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, specialization, username);
    }
}
