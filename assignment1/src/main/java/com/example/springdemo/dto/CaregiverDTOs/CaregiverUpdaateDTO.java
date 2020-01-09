package com.example.springdemo.dto.CaregiverDTOs;

import java.util.Objects;

public class CaregiverUpdaateDTO {

    private String name;
    private String gender;
    private String address;

    public CaregiverUpdaateDTO() {
    }

    public CaregiverUpdaateDTO(String name, String gender, String address) {
        this.name = name;
        this.gender = gender;
        this.address = address;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CaregiverUpdaateDTO that = (CaregiverUpdaateDTO) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(gender, that.gender) &&
                Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, gender, address);
    }
}
