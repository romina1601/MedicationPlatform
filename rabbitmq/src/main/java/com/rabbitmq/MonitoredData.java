package com.rabbitmq;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;

public class MonitoredData {

    private Integer patientId;
    private Date startTime;
    private Date endTime;
    private String activity;

    public MonitoredData() {
    }

    public MonitoredData(@JsonProperty("patient_id") int id,
                         @JsonProperty("start_date") Date startTime,
                         @JsonProperty("end_date") Date endTime,
                         @JsonProperty("activity") String activity) {

        this.patientId = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activity = activity;
    }


    public MonitoredData(Integer patientId, Date startTime, Date endTime, String activity) {
        this.patientId = patientId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activity = activity;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
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

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    @Override
    public String toString() {
        return "com.rabbitmq.MonitoredData{" +
                "patientId=" + patientId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", activity='" + activity + '\'' +
                '}';
    }

    public String toJSONString()
    {
        JSONObject jsonObject = new JSONObject();
        try
        {
            jsonObject.put("patient_id",  this.patientId);
            jsonObject.put("start_time", this.startTime);
            jsonObject.put("end_time", this.endTime);
            jsonObject.put("activity", this.activity);
        }
        catch (JSONException e)
        {
            e.getMessage();
        }

        return jsonObject.toString();
    }

    public List<MonitoredData> readData(String filename) {
        List<String> listFile = new ArrayList<>();
        listFile.add("4	2011-11-28 02:27:59		2011-11-28 10:18:11		Sleeping");
        listFile.add("4	2011-11-28 02:27:59		2011-11-28 10:18:11		Sleeping");
        listFile.add("4	2011-11-28 02:27:59		2011-11-28 10:18:11		Sleeping");
        listFile.add("4	2011-11-28 02:27:59		2011-11-28 10:18:11		Sleeping");
        listFile.add("4	2011-11-28 02:27:59		2011-11-28 10:18:11		Sleeping");
        listFile.add("4	2011-11-28 02:27:59		2011-11-28 10:18:11		Sleeping");
        listFile.add("4	2011-11-28 02:27:59		2011-11-28 10:18:11		Sleeping");
        listFile.add("4	2011-11-28 02:27:59		2011-11-28 10:18:11		Sleeping");

        List<MonitoredData> activities = new ArrayList<>();
        DateFormat format = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
        Integer patientId;
        String start, end, activity;




        //Resource resource = new ClassPathResource("classpath:"+filename);

        /*try (Stream<String> stream = Files.lines(Paths.get(ResourceUtils.getFile("classpath:"+filename).getPath()))) {

            listFile = stream.collect( Collectors.toList() );

            for (String line : listFile) {
                MonitoredData m = new MonitoredData();
                patientId = parseInt(line.substring(0, 1));
                start = line.substring( 2, 21 );
                end = line.substring( 23, 42 );
                activity = line.substring( 44, line.length() );
                activity = activity.replaceAll( "\\s+", "" );

                Date startD = format.parse( start );
                Date endD = format.parse( end );


                m.setPatientId(patientId);
                m.setStartTime( startD );
                m.setEndTime( endD );
                m.setActivity( activity );

                activities.add( m );
            }


            return activities;

        } catch (IOException e) {
            e.getMessage();
        } catch (ParseException e) {
            e.getMessage();
        }

        return null;*/

        try {
            for (String line : listFile) {
                MonitoredData m = new MonitoredData();
                patientId = parseInt(line.substring(0, 1));
                start = line.substring(2, 21);
                end = line.substring(23, 42);
                activity = line.substring(44, line.length());
                activity = activity.replaceAll("\\s+", "");

                Date startD = format.parse(start);
                Date endD = format.parse(end);


                m.setPatientId(patientId);
                m.setStartTime(startD);
                m.setEndTime(endD);
                m.setActivity(activity);

                activities.add(m);
            }


            return activities;
        }
        catch (ParseException e) {
            e.getMessage();
        }

        return null;
    }

    /*public static void main(String[] args) {

        MonitoredData md = new MonitoredData();
        List<MonitoredData> list = md.readData( "activity.txt" );

    }*/
}




