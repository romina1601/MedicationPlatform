package com.example.springdemo.controller;


//import com.example.springdemo.configuration.SwaggerTags;
import com.example.springdemo.dto.MedicationDTOs.GrpcMedicationDTO;
import com.example.springdemo.dto.MonitoredDataDTOs.MonitoredDataDTO;
import com.example.springdemo.dto.PatientDTOs.JustPatientDTO;
import com.example.springdemo.dto.PatientDTOs.PatientInsertDTO;
import com.example.springdemo.dto.PatientDTOs.PatientUpdateDTO;
import com.example.springdemo.dto.PatientDTOs.PatientViewDTO;
import com.example.springdemo.dto.RecommendationDTOs.RecommendationDTO;
import com.example.springdemo.dto.UsersDTOs.UsersDTO;
import com.example.springdemo.entities.GrpcMedication;
import com.example.springdemo.entities.MonitoredData;
import com.example.springdemo.entities.Patient;
import com.example.springdemo.entities.enums.UsersType;
import com.example.springdemo.errorhandler.ResourceNotFoundException;
import com.example.springdemo.services.PatientService;
import com.example.springdemo.soap.SoapClient;
import generatedSOAPResources.GetMedicationHistoryRequest;
import generatedSOAPResources.GetMedicationHistoryResponse;
import generatedSOAPResources.Medication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/patient")
public class PatientController {

    private final PatientService patientService;
    private final SoapClient soapClient;

    @Autowired

    public PatientController(PatientService patientService, SoapClient soapClient) {
        this.patientService = patientService;
        this.soapClient = soapClient;
    }

    //@ApiOperation(value = "findAllPatient"/*, tags = SwaggerTags.PATIENT_TAG*/)
    @GetMapping
    public List<PatientViewDTO> findAllPatient() {
        return patientService.findAll();
    }

    //@ApiOperation(value = "findPatientById"/*, tags = SwaggerTags.PATIENT_TAG*/)
    @GetMapping(value = "/{patientId}")
    public ResponseEntity<?> findPatientById(@PathVariable("patientId")Integer patientId)
    {
        try
        {
            PatientViewDTO patientViewDTO = patientService.findPatientById(patientId);
            return new ResponseEntity<>(patientViewDTO, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    //@ApiOperation(value = "insertPatient"/*, tags = SwaggerTags.PATIENT_TAG*/)
    @PostMapping(value="/{username}/{password}/{role}/{caregiverId}")
    public ResponseEntity<?> insertPatient(@RequestBody PatientInsertDTO patientInsertDTO,
                                           @PathVariable String username,
                                           @PathVariable String password,
                                           @PathVariable UsersType role,
                                           @PathVariable Integer caregiverId) {
        JustPatientDTO justPatientDTO;
        UsersDTO usersDTO = new UsersDTO(username, password, role);
        try {
            justPatientDTO = patientService.insert( patientInsertDTO, usersDTO, caregiverId);
            return new ResponseEntity<>(justPatientDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }


    //@ApiOperation(value = "updatePatient"/*, tags = SwaggerTags.PATIENT_TAG*/)
    @PutMapping(value = "/{patientId}")
    public ResponseEntity<?>updatePatient(@PathVariable("patientId") Integer patientId,
                                         @RequestBody PatientUpdateDTO patientUpdateDTO)
    {
        try {
            JustPatientDTO justPatientDTO = patientService.update(patientId, patientUpdateDTO);
            return new ResponseEntity<>(justPatientDTO, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception ex)
        {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    //@ApiOperation(value = "deletePatient"/*, tags = SwaggerTags.PATIENT_TAG*/)
    @DeleteMapping(value = "/{patientId}")
    public ResponseEntity<?> deletePatient(@PathVariable("patientId") Integer patientId)
    {
        try {
            patientService.delete(patientId);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (ResourceNotFoundException rne) {
            return new ResponseEntity<>(rne.getMessage(),HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping(value = "/username/{username}")
    public ResponseEntity<?> findPatientByUsername(@PathVariable("username")String username)
    {
        try
        {
            PatientViewDTO patientViewDTO = patientService.findPatientByUsername(username);
            return new ResponseEntity<>(patientViewDTO, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value="/{patientId}/{caregiverId}")
    public ResponseEntity<?> changeCaregiver(@PathVariable Integer patientId,
                                             @PathVariable Integer caregiverId){
        PatientViewDTO patientViewDTO;
        try {
            patientViewDTO = patientService.changeCaregiver( patientId, caregiverId);
            return new ResponseEntity<>(patientViewDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }


    /************   ASSIGNMENT 4   ****************/
    @PostMapping(value="/getMedicationHistory/{patientId}")
    //public GetMedicationHistoryResponse getMedicationHistory(@PathVariable ("patientId") Integer patientId)
    public List<GrpcMedicationDTO> getMedicationHistory(@PathVariable ("patientId") Integer patientId)
    {
        List<GrpcMedicationDTO> medications = soapClient.getMedicationHistory(patientId);

        return medications;
    }

    @PostMapping(value="/getPatientHistory/{patientId}")
    public List<MonitoredDataDTO> getPatientHistory(@PathVariable ("patientId") Integer patientId)
    {
        List<MonitoredDataDTO> activities = soapClient.getPatientHistory(patientId);

        return activities;
    }

    @PostMapping(value="/insertRecommendation/{patientId}/{description}")
    public ResponseEntity<?> insertRecommendation(@PathVariable ("patientId") Integer patientId,
                                                  @PathVariable ("description") String description)
    {
        RecommendationDTO recommendationDTO;
        try {
            recommendationDTO = patientService.insertRecommendation( patientId, description);
            return new ResponseEntity<>(recommendationDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping(value = "/getRecommendation/{patientId}")
    public ResponseEntity<?> getRecommendationByPatientId(@PathVariable("patientId")Integer patientId)
    {
        try
        {
            RecommendationDTO recommendationDTO = patientService.getRecommendationByPatientById(patientId);
            return new ResponseEntity<>(recommendationDTO, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }





}
