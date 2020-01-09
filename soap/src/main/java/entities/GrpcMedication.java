package entities;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "grpc_medication")
public class GrpcMedication {

    @Id
    @Column(name = "grpc_medication_id", unique = true, nullable = false)
    private Integer grpcMedicationId;

    @Column(name = "patient_id")
    private Integer patientId;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "status", length = 100)
    private String status;

    public GrpcMedication() {
    }

    public GrpcMedication(Integer grpcMedicationId, Integer patientId, String name, String status) {
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
        return "GrpcMedication{" +
                "grpcMedicationId=" + grpcMedicationId +
                ", patientId=" + patientId +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrpcMedication that = (GrpcMedication) o;
        return Objects.equals(grpcMedicationId, that.grpcMedicationId) &&
                Objects.equals(patientId, that.patientId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(grpcMedicationId, patientId, name, status);
    }
}