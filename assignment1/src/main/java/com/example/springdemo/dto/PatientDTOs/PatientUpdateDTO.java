package com.example.springdemo.dto.PatientDTOs;

import java.util.Objects;

public class PatientUpdateDTO {

    private String name;
    private String gender;
    private String address;
    private String medicalRecord;


    public PatientUpdateDTO() {
    }

    public PatientUpdateDTO(String name, String gender, String address, String medicalRecord) {
        this.name = name;
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
        PatientUpdateDTO that = (PatientUpdateDTO) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(gender, that.gender) &&
                Objects.equals(address, that.address) &&
                Objects.equals(medicalRecord, that.medicalRecord);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, gender, address, medicalRecord);
    }
}
