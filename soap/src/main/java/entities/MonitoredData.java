package entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "monitored_data")
public class MonitoredData {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "monitored_data_id", unique = true, nullable = false)
    private Integer monitoredDataId;

    @Column(name = "patient_id")
    private Integer patientId;

    @Column(name = "activity")
    private String activity;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;

    @Column(name = "behavior")
    private String behavior;

    public MonitoredData() {
    }

    public MonitoredData(Integer patientId, String activity,
                         Date startTime, Date endTime, String behavior) {
        this.patientId = patientId;
        this.activity = activity;
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

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
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
        return "MonitoredData{" +
                "monitoredDataId=" + monitoredDataId +
                ", patientId=" + patientId +
                ", activity='" + activity + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", behavior=" + behavior +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonitoredData that = (MonitoredData) o;
        return Objects.equals(monitoredDataId, that.monitoredDataId) &&
                Objects.equals(patientId, that.patientId) &&
                Objects.equals(activity, that.activity) &&
                Objects.equals(startTime, that.startTime) &&
                Objects.equals(endTime, that.endTime) &&
                Objects.equals(behavior, that.behavior);
    }

    @Override
    public int hashCode() {
        return Objects.hash(monitoredDataId, patientId, activity, startTime, endTime, behavior);
    }
}
