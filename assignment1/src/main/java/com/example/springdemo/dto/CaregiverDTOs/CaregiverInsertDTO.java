package com.example.springdemo.dto.CaregiverDTOs;

import java.util.Objects;

public class CaregiverInsertDTO {

    private String name;
    private String birthDate;
    private String gender;
    private String address;

    public CaregiverInsertDTO() {
    }

    public CaregiverInsertDTO(String name, String birthDate, String gender, String address) {
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CaregiverInsertDTO that = (CaregiverInsertDTO) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(birthDate, that.birthDate) &&
                Objects.equals(gender, that.gender) &&
                Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthDate, gender, address);
    }
}
