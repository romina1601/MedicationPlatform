package com.example.springdemo.dto.PatientDTOs;

import java.util.Objects;

public class PatientInsertDTO {

    private String name;
    private String birthDate;
    private String gender;
    private String address;
    private String medicalRecord;

    public PatientInsertDTO() {
    }

    public PatientInsertDTO(String name, String birthDate, String gender, String address, String medicalRecord) {
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.medicalRecord = medicalRecord;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientInsertDTO that = (PatientInsertDTO) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(birthDate, that.birthDate) &&
                Objects.equals(gender, that.gender) &&
                Objects.equals(address, that.address) &&
                Objects.equals(medicalRecord, that.medicalRecord);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthDate, gender, address, medicalRecord);
    }
}
