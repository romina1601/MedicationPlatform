package com.example.springdemo.dto.CaregiverDTOs;

import com.example.springdemo.entities.Users;

import java.util.Objects;

public class CaregiverDTO {

    private Integer caregiverId;
    private String name;
    private String birthDate;
    private String gender;
    private String address;
    private Users user;

    public CaregiverDTO() {
    }

    public CaregiverDTO(Integer caregiverId, String name, String birthDate,
                        String gender, String address, Users user) {
        this.caregiverId = caregiverId;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.user = user;
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
        CaregiverDTO that = (CaregiverDTO) o;
        return Objects.equals(caregiverId, that.caregiverId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(birthDate, that.birthDate) &&
                Objects.equals(gender, that.gender) &&
                Objects.equals(address, that.address) &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(caregiverId, name, birthDate, gender, address, user);
    }
}
