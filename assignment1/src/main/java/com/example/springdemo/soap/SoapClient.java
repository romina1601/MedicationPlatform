package com.example.springdemo.soap;

import com.example.springdemo.dto.MedicationDTOs.GrpcMedicationDTO;
import com.example.springdemo.dto.MonitoredDataDTOs.MonitoredDataDTO;
import com.example.springdemo.entities.GrpcMedication;
import com.example.springdemo.entities.MonitoredData;
import generatedSOAPResources.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class SoapClient {

    @Autowired
    private Jaxb2Marshaller marshaller;

    private WebServiceTemplate template;


    //public GetMedicationHistoryResponse getMedicationHistory (GetMedicationHistoryRequest request)
    public List<GrpcMedicationDTO> getMedicationHistory (Integer patientId)
    {
        template = new WebServiceTemplate(marshaller);

        GetMedicationHistoryRequest request = new GetMedicationHistoryRequest();
        request.setPatientId(BigInteger.valueOf(patientId));

        GetMedicationHistoryResponse response = (GetMedicationHistoryResponse)
                template.marshalSendAndReceive("http://localhost:8081/ws", request);

        List<GrpcMedicationDTO> medications = new ArrayList<>();
        for (Medication m : response.getMedication())
        {
            medications.add(new GrpcMedicationDTO(
                    m.getMedicationId().intValue(),
                    m.getPatientId().intValue(),
                    m.getName(),
                    m.getStatus()
            ));
        }
        return medications;
    }

    public List<MonitoredDataDTO> getPatientHistory (Integer patientId)
    {
        template = new WebServiceTemplate(marshaller);

        GetPatientHistoryRequest request = new GetPatientHistoryRequest();
        request.setPatientId(BigInteger.valueOf(patientId));

        GetPatientHistoryResponse response = (GetPatientHistoryResponse)
                template.marshalSendAndReceive("http://localhost:8081/ws", request);

        List<MonitoredDataDTO> activities = new ArrayList<>();
        DateFormat format = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss.S");
        for (Activity a : response.getActivity())
        {

            activities.add(new MonitoredDataDTO(
                    a.getActivityId().intValue(),
                    a.getPatientId().intValue(),
                    a.getName(),
                    a.getStartTime(),
                    a.getEndTime(),
                    a.getBehavior()
            ));

        }

        return activities;
    }

}
