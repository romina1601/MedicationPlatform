package com.example.springdemo.dto.MonitoredDataDTOs;

import java.util.Date;
import java.util.Objects;

public class MonitoredDataDTO {

    private Integer monitoredDataId;
    private Integer patientId;
    private String name;
    private String startTime;
    private String endTime;
    private String behavior;

    public MonitoredDataDTO() {
    }

    public MonitoredDataDTO(Integer monitoredDataId, Integer patientId, String name, String startTime, String endTime, String behavior) {
        this.monitoredDataId = monitoredDataId;
        this.patientId = patientId;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.behavior = behavior;
    }

    public Integer getMonitoredDataId() {
        return monitoredDataId;
    }

    public void setMonitoredDataId(Integer monitoredDataId) {
        this.monitoredDataId = monitoredDataId;
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getBehavior() {
        return behavior;
    }

    public void setBehavior(String behavior) {
        this.behavior = behavior;
    }

    @Override
    public String toString() {
        return "MonitoredDataDTO{" +
                "monitoredDataId=" + monitoredDataId +
                ", patientId=" + patientId +
                ", name='" + name + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", behavior='" + behavior + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonitoredDataDTO that = (MonitoredDataDTO) o;
        return Objects.equals(monitoredDataId, that.monitoredDataId) &&
                Objects.equals(patientId, that.patientId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(startTime, that.startTime) &&
                Objects.equals(endTime, that.endTime) &&
                Objects.equals(behavior, that.behavior);
    }

    @Override
    public int hashCode() {
        return Objects.hash(monitoredDataId, patientId, name, startTime, endTime, behavior);
    }
}
