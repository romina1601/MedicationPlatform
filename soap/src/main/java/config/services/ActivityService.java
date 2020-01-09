package config.services;

import entities.MonitoredData;
import org.dsassignment4.soap.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.ActivityRepository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;

    @Autowired
    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public List<Activity> getPatientHistoryResponse (Integer patientId)
    {
        List<MonitoredData> allActivities;
        List<Activity> patientActivities = new ArrayList<>();

        allActivities = this.activityRepository.findAll();

        for(MonitoredData m: allActivities)
        {
            if (m.getPatientId() == patientId)
            {
                Activity activity = new Activity();
                activity.setActivityId(BigInteger.valueOf(m.getMonitoredDataId()));
                activity.setPatientId(BigInteger.valueOf(m.getPatientId()));
                activity.setName(m.getActivity());
                activity.setStartTime(m.getStartTime().toString());
                activity.setEndTime(m.getEndTime().toString());
                activity.setBehavior(m.getBehavior());
                patientActivities.add(activity);
            }
        }
        return patientActivities;
    }
}
