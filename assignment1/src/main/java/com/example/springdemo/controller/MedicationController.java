package com.example.springdemo.controller;

import com.example.springdemo.dto.MedicationDTOs.MedicationDTO;
import com.example.springdemo.dto.MedicationDTOs.MedicationInsertDTO;
import com.example.springdemo.errorhandler.ResourceNotFoundException;
import com.example.springdemo.services.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/medication")
public class MedicationController {

    private final MedicationService medicationService;

    @Autowired

    public MedicationController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }


    //@ApiOperation(value = "findAllMedications"/*, tags = SwaggerTags.MEDICATION_TAG*/)
    @GetMapping
    public List<MedicationDTO> findAllMedications() {
        return medicationService.findAll();
    }


    //@ApiOperation(value = "findMedicationById"/*, tags = SwaggerTags.MEDICATION_TAG*/)
    @GetMapping(value = "/{medicationId}")
    public ResponseEntity<?> findMedicationById(@PathVariable("medicationId")Integer medicationId)
    {
        try
        {
            MedicationDTO medicationDTO = medicationService.findMedicationById(medicationId);
            return new ResponseEntity<>(medicationDTO, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    //@ApiOperation(value = "insertMedication"/*, tags = SwaggerTags.MEDICATION_TAG*/)
    @PostMapping()
    public ResponseEntity<?> insertMedication(@RequestBody MedicationInsertDTO medicationInsertDTO) {
        MedicationDTO medicationDTOToBeInserted;
        try {
            medicationDTOToBeInserted = medicationService.insert(medicationInsertDTO);
            return new ResponseEntity<>(medicationDTOToBeInserted, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }


    //@ApiOperation(value = "updateMedication"/*, tags = SwaggerTags.MEDICATION_TAG*/)
    @PutMapping(value = "/{medicationId}")
    public ResponseEntity<?>updateUser(@PathVariable("medicationId") Integer medicationId,
                                       @RequestBody MedicationInsertDTO medicationInsertDTO)
    {
        try {
            MedicationDTO updatedMedication = medicationService.update(medicationId, medicationInsertDTO);
            return new ResponseEntity<>(updatedMedication, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception ex)
        {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    //@ApiOperation(value = "deleteMedication"/*, tags = SwaggerTags.MEDICATION_TAG*/)
    @DeleteMapping(value = "/{medicationId}")
    public ResponseEntity<?> deleteUser(@PathVariable("medicationId") Integer medicationId)
    {
        try {
            medicationService.delete(medicationId);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (ResourceNotFoundException rne) {
            return new ResponseEntity<>(rne.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/{medicationId}/{medPlanId}")
    public ResponseEntity<?>addMedicationToMedPlan(@PathVariable("medicationId") Integer medicationId,
                                                   @PathVariable("medPlanId") Integer medPlanId)
    {
        try {
            MedicationDTO medicationDTO = medicationService.addMedicationToMedPlan(medicationId, medPlanId);
            return new ResponseEntity<>(medicationDTO, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception ex)
        {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
