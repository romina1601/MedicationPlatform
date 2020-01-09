package com.example.springdemo.dto.CaregiverDTOs;

import com.example.springdemo.dto.PatientDTOs.JustPatientDTO;
import com.example.springdemo.dto.PatientDTOs.PatientDTO;

import java.util.List;
import java.util.Objects;

public class CaregiverViewDTO {

    private Integer caregiverId;
    private String name;
    private String birthDate;
    private String gender;
    private String address;
    private String username;
    private List<JustPatientDTO> patients;

    public CaregiverViewDTO() {
    }

    public CaregiverViewDTO(Integer caregiverId, String name, String birthDate,
                            String gender, String address, String username,
                            List<JustPatientDTO> patients) {
        this.caregiverId = caregiverId;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.username = username;
        this.patients = patients;
    }

    public CaregiverViewDTO(String name, String birthDate, String gender,
                            String address, String username,
                            List<JustPatientDTO> patients) {
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.username = username;
        this.patients = patients;
    }

    public Integer getCaregiverId() {
        return caregiverId;
    }

    public void setCaregiverId(Integer caregiverId) {
        this.caregiverId = caregiverId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<JustPatientDTO> getPatients() {
        return patients;
    }

    public void setPatients(List<JustPatientDTO> patients) {
        this.patients = patients;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CaregiverViewDTO that = (CaregiverViewDTO) o;
        return Objects.equals(caregiverId, that.caregiverId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(birthDate, that.birthDate) &&
                Objects.equals(gender, that.gender) &&
                Objects.equals(address, that.address) &&
                Objects.equals(username, that.username) &&
                Objects.equals(patients, that.patients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(caregiverId, name, birthDate, gender, address, username, patients);
    }
}
