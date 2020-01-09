package com.example.springdemo.dto.CaregiverDTOs;

import java.util.Objects;

public class CaregiverForPatientDTO {

    private Integer caregiverId;
    private String name;


    public CaregiverForPatientDTO() {
    }

    public CaregiverForPatientDTO(Integer caregiverId, String name) {
        this.caregiverId = caregiverId;
        this.name = name;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CaregiverForPatientDTO that = (CaregiverForPatientDTO) o;
        return Objects.equals(caregiverId, that.caregiverId) &&
                Objects.equals(name, that.name) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(caregiverId, name);
    }
}
