package com.example.springdemo.controller;


//import com.example.springdemo.configuration.SwaggerTags;
import com.example.springdemo.dto.MedicationDTOs.MedicationDTO;
import com.example.springdemo.dto.MedicationPlanDTOs.MedicationPlanDTO;
import com.example.springdemo.dto.MedicationPlanDTOs.MedicationPlanInsertDTO;
import com.example.springdemo.dto.MedicationPlanDTOs.MedicationPlanUpdateDTO;
import com.example.springdemo.errorhandler.ResourceNotFoundException;
import com.example.springdemo.services.MedicationPlanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Api()
@RestController
@CrossOrigin
@RequestMapping(value = "/medicationPlan")
public class MedicationPlanController {

    private final MedicationPlanService medicationPlanService;

    @Autowired

    public MedicationPlanController(MedicationPlanService medicationPlanService) {
        this.medicationPlanService = medicationPlanService;
    }


    //@ApiOperation(value = "findAllMedicationPlans"/*, tags = SwaggerTags.MEDICATION_PLAN_TAG*/)
    @GetMapping
    public List<MedicationPlanDTO> findAllMedicationPlans() {
        return medicationPlanService.findAllFetch();
    }


    //@ApiOperation(value = "findMedicationPlanById"/*, tags = SwaggerTags.MEDICATION_PLAN_TAG*/)
    @GetMapping(value = "/{medPlanId}")
    public ResponseEntity<?> findMedPlanById(@PathVariable("medPlanId")Integer medPlanId)
    {
        try
        {
            MedicationPlanDTO medicationPlanDTO = medicationPlanService.findMedPlanById(medPlanId);
            return new ResponseEntity<>(medicationPlanDTO, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    //@ApiOperation(value = "insertMedPlan"/*, tags = SwaggerTags.MEDICATION_PLAN_TAG*/)
    @PostMapping(value="/{patientId}")
    public ResponseEntity<?> insertMedPlan(@PathVariable Integer patientId,
                                           @RequestBody MedicationPlanInsertDTO medicationPlanInsertDTO)
    {
        MedicationPlanUpdateDTO medicationPlanToInsert;
        try {
            medicationPlanToInsert = medicationPlanService.insert(medicationPlanInsertDTO, patientId);
            return new ResponseEntity<>(medicationPlanToInsert, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }


    //@ApiOperation(value = "updateMedPlan"/*, tags = SwaggerTags.MEDICATION_PLAN_TAG*/)
    @PutMapping(value = "/{medPlanId}")
    public ResponseEntity<?>updateMedPlan(@PathVariable("medPlanId") Integer medPlanId,
                                         @RequestBody MedicationPlanUpdateDTO medicationPlanUpdateDTO)
    {
        try {
            MedicationPlanDTO updatedMedPlan = medicationPlanService.updateMedPlan(medPlanId, medicationPlanUpdateDTO);
            return new ResponseEntity<>(updatedMedPlan, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception ex)
        {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //@ApiOperation(value = "deleteMedPlan"/*, tags = SwaggerTags.MEDICATION_PLAN_TAG*/)
    @DeleteMapping(value = "/{medPlanId}")
    public ResponseEntity<?> deleteUser(@PathVariable("medPlanId") Integer medPlanId)
    {
        try {
            medicationPlanService.deleteMedPlan(medPlanId);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (ResourceNotFoundException rne) {
            return new ResponseEntity<>(rne.getMessage(),HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
