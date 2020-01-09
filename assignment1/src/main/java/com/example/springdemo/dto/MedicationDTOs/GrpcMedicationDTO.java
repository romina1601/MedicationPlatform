package com.example.springdemo.dto.MedicationDTOs;

public class GrpcMedicationDTO {

    private Integer grpcMedicationId;
    private Integer patientId;
    private String name;
    private String status;

    public GrpcMedicationDTO() {
    }

    public GrpcMedicationDTO(Integer grpcMedicationId, Integer patientId, String name, String status) {
        this.grpcMedicationId = grpcMedicationId;
        this.patientId = patientId;
        this.name = name;
        this.status = status;
    }

    public Integer getGrpcMedicationId() {
        return grpcMedicationId;
    }

    public void setGrpcMedicationId(Integer grpcMedicationId) {
        this.grpcMedicationId = grpcMedicationId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "GrpcMedicationDTO{" +
                "grpcMedicationId=" + grpcMedicationId +
                ", patientId=" + patientId +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
