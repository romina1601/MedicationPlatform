package com.example.springdemo.controller;

import com.example.springdemo.dto.CaregiverDTOs.*;
import com.example.springdemo.dto.UsersDTOs.UsersDTO;
import com.example.springdemo.entities.enums.UsersType;
import com.example.springdemo.errorhandler.ResourceNotFoundException;
import com.example.springdemo.services.CaregiverService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/caregiver")
public class CaregiverController {

    private final CaregiverService caregiverService;

    @Autowired
    public CaregiverController(CaregiverService caregiverService) {
        this.caregiverService = caregiverService;
    }

    //@ApiOperation(value = "findAllCaregivers"/*, tags = SwaggerTags.CAREGIVER_TAG*/)
    @GetMapping
    public List<CaregiverViewDTO> findAllCaregivers() {
        return caregiverService.findAll();
    }

    //@ApiOperation(value = "findCaregiverById"/*, tags = SwaggerTags.CAREGIVER_TAG*/)
    @GetMapping(value = "/{caregiverId}")
    public ResponseEntity<?> findDoctorById(@PathVariable("caregiverId")Integer caregiverId)
    {
        try
        {
            CaregiverViewDTO caregiverViewDTO = caregiverService.findCaregiverById(caregiverId);
            return new ResponseEntity<>(caregiverViewDTO, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }


    //@ApiOperation(value = "insertCaregiver"/*, tags = SwaggerTags.CAREGIVER_TAG*/)
    @PostMapping(value="/{username}/{password}/{role}")
    public ResponseEntity<?> insertCaregiver(@RequestBody CaregiverInsertDTO caregiverInsertDTO,
                                             @PathVariable String username,
                                             @PathVariable String password,
                                             @PathVariable UsersType role) {
        JustCaregiverDTO justCaregiverDTO;
        UsersDTO usersDTO = new UsersDTO(username, password, role);
        try {
            justCaregiverDTO = caregiverService.insert( caregiverInsertDTO, usersDTO);
            return new ResponseEntity<>(justCaregiverDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }


    //@ApiOperation(value = "updateCaregiver"/*, tags = SwaggerTags.CAREGIVER_TAG*/)
    @PutMapping(value = "/{caregiverId}")
    public ResponseEntity<?>updateDoctor(@PathVariable("caregiverId") Integer caregiverId,
                                         @RequestBody CaregiverUpdaateDTO caregiverUpdaateDTO)
    {
        try {
            CaregiverViewDTO updatedCaregiver = caregiverService.update(caregiverId, caregiverUpdaateDTO);
            return new ResponseEntity<>(updatedCaregiver, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception ex)
        {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    //@ApiOperation(value = "deleteCaregiver"/*, tags = SwaggerTags.CAREGIVER_TAG*/)
    @DeleteMapping(value = "/{caregiverId}")
    public ResponseEntity<?> deleteUser(@PathVariable("caregiverId") Integer caregiverId)
    {
        try {
            caregiverService.delete(caregiverId);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (ResourceNotFoundException rne) {
            return new ResponseEntity<>(rne.getMessage(),HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/username/{username}")
    public ResponseEntity<?> findCaregiverByUsername(@PathVariable("username")String username)
    {
        try
        {
            CaregiverViewDTO caregiverViewDTO = caregiverService.findCaregiverByUsername(username);
            return new ResponseEntity<>(caregiverViewDTO, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
