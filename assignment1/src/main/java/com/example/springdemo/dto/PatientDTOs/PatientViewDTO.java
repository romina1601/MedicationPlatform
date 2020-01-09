package com.example.springdemo.dto.PatientDTOs;

import com.example.springdemo.dto.CaregiverDTOs.CaregiverForPatientDTO;
import com.example.springdemo.dto.MedicationPlanDTOs.MedicationPlanForPatientDTO;

import java.util.List;
import java.util.Objects;

public class PatientViewDTO {

    private Integer patientId;
    private String name;
    private String birthDate;
    private String gender;
    private String address;
    private String medicalRecord;
    private String username;
    private CaregiverForPatientDTO caregiverDTO;
    private List<MedicationPlanForPatientDTO> medicationPlans;

    public PatientViewDTO() {
    }

    public PatientViewDTO(Integer patientId, String name, String birthDate, String gender, String address,
                          String medicalRecord, String username, CaregiverForPatientDTO caregiverDTO,
                          List<MedicationPlanForPatientDTO> medicationPlans) {
        this.patientId = patientId;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.medicalRecord = medicalRecord;
        this.username = username;
        this.caregiverDTO = caregiverDTO;
        this.medicationPlans = medicationPlans;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public CaregiverForPatientDTO getCaregiverDTO() {
        return caregiverDTO;
    }

    public void setCaregiverDTO(CaregiverForPatientDTO caregiverDTO) {
        this.caregiverDTO = caregiverDTO;
    }

    public List<MedicationPlanForPatientDTO> getMedicationPlans() {
        return medicationPlans;
    }

    public void setMedicationPlans(List<MedicationPlanForPatientDTO> medicationPlans) {
        this.medicationPlans = medicationPlans;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientViewDTO that = (PatientViewDTO) o;
        return Objects.equals(patientId, that.patientId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(birthDate, that.birthDate) &&
                Objects.equals(gender, that.gender) &&
                Objects.equals(address, that.address) &&
                Objects.equals(medicalRecord, that.medicalRecord) &&
                Objects.equals(username, that.username) &&
                Objects.equals(caregiverDTO, that.caregiverDTO) &&
                Objects.equals(medicationPlans, that.medicationPlans);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patientId, name, birthDate, gender, address, medicalRecord, username, caregiverDTO, medicationPlans);
    }
}
