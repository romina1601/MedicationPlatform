package com.example.springdemo.dto.PatientDTOs;

import com.example.springdemo.dto.CaregiverDTOs.CaregiverDTO;
import com.example.springdemo.dto.UsersDTOs.UsersDTO;

import java.util.Objects;

public class
PatientDTO {

    private Integer patientId;
    private String name;
    private String birthDate;
    private String gender;
    private String address;
    private String medicalRecord;
    private UsersDTO usersDTO;
    private CaregiverDTO caregiverDTO;


    public PatientDTO() {
    }

    public PatientDTO(Integer patientId, String name, String birthDate,
                      String gender, String address, String medicalRecord,
                      UsersDTO usersDTO, CaregiverDTO caregiverDTO) {
        this.patientId = patientId;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.medicalRecord = medicalRecord;
        this.usersDTO = usersDTO;
        this.caregiverDTO = caregiverDTO;
    }


    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
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

    public String getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(String medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    public UsersDTO getUsersDTO() {
        return usersDTO;
    }

    public void setUsersDTO(UsersDTO usersDTO) {
        this.usersDTO = usersDTO;
    }

    public CaregiverDTO getCaregiverDTO() {
        return caregiverDTO;
    }

    public void setCaregiverDTO(CaregiverDTO caregiverDTO) {
        this.caregiverDTO = caregiverDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientDTO that = (PatientDTO) o;
        return Objects.equals(patientId, that.patientId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(birthDate, that.birthDate) &&
                Objects.equals(gender, that.gender) &&
                Objects.equals(address, that.address) &&
                Objects.equals(medicalRecord, that.medicalRecord) &&
                Objects.equals(usersDTO, that.usersDTO) &&
                Objects.equals(caregiverDTO, that.caregiverDTO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patientId, name, birthDate, gender,
                            address, medicalRecord, usersDTO, caregiverDTO);
    }
}
