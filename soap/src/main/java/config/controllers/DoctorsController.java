package config.controllers;


import config.services.ActivityService;
import config.services.GrpcMedicationService;
import org.dsassignment4.soap.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class DoctorsController {

    private static final String NAMESPACE_URI = "http://dsassignment4.org/soap";
    private GrpcMedicationService grpcMedicationService;
    private ActivityService activityService;

    @Autowired
    public DoctorsController(GrpcMedicationService grpcMedicationService,
                             ActivityService activityService) {
        this.grpcMedicationService = grpcMedicationService;
        this.activityService = activityService;
    }


    @PayloadRoot(localPart = "getMedicationHistoryRequest", namespace = NAMESPACE_URI )
    @ResponsePayload
    public GetMedicationHistoryResponse getMedicationHistory
            (@RequestPayload GetMedicationHistoryRequest getMedicationHistoryRequest)
    {
        GetMedicationHistoryResponse response = new GetMedicationHistoryResponse();
        List<Medication> patientMedication =
                this.grpcMedicationService.getMedicationHistoryResponse(getMedicationHistoryRequest.getPatientId().intValue());
        response.getMedication().addAll(patientMedication);
        return response;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPatientHistoryRequest")
    @ResponsePayload
    public GetPatientHistoryResponse getPatientHistoryResponse
            (@RequestPayload GetPatientHistoryRequest getPatientHistoryRequest) {

        GetPatientHistoryResponse response = new GetPatientHistoryResponse();

        List<Activity> patientActivities =
                this.activityService.getPatientHistoryResponse(getPatientHistoryRequest.getPatientId().intValue());

        response.getActivity().addAll(patientActivities);

        return response;
    }
}
