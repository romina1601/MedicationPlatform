package entities;

import java.util.Objects;

public class MedicationEntity {

    private Integer medicationId;
    private String name;
    private String intakeInterval;
    private Boolean taken;

    public MedicationEntity() {
    }

    public MedicationEntity(Integer medicationId, String name, String intakeInterval, Boolean taken) {
        this.medicationId = medicationId;
        this.name = name;
        this.intakeInterval = intakeInterval;
        this.taken = taken;
    }

    public Integer getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(Integer medicationId) {
        this.medicationId = medicationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntakeInterval() {
        return intakeInterval;
    }

    public void setIntakeInterval(String intakeInterval) {
        this.intakeInterval = intakeInterval;
    }

    public Boolean isTaken() {
        return taken;
    }

    public void setTaken(Boolean taken) {
        this.taken = taken;
    }

    @Override
    public String toString() {
        return "MedicationEntity{" +
                "medicationId=" + medicationId +
                ", name='" + name + '\'' +
                ", intakeInterval='" + intakeInterval + '\'' +
                ", taken=" + taken +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicationEntity that = (MedicationEntity) o;
        return taken == that.taken &&
                Objects.equals(medicationId, that.medicationId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(intakeInterval, that.intakeInterval);
    }

    @Override
    public int hashCode() {
        return Objects.hash(medicationId, name, intakeInterval, taken);
    }
}
