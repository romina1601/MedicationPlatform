package com.example.springdemo.controller;


import com.example.springdemo.dto.DoctorDTOs.DoctorDTO;
import com.example.springdemo.dto.DoctorDTOs.DoctorViewDTO;
import com.example.springdemo.dto.DoctorDTOs.JustDoctorDTO;
import com.example.springdemo.dto.UsersDTOs.UsersDTO;
import com.example.springdemo.errorhandler.ResourceNotFoundException;
import com.example.springdemo.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Api()
@RestController
@CrossOrigin
@RequestMapping(value = "/doctor")
public class DoctorController {

    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    //@ApiOperation(value = "findAllDoctors"/*, tags = SwaggerTags.DOCTOR_TAG*/)
    @GetMapping
    public List<DoctorViewDTO> findAllDoctors() {
        return doctorService.findAll();
    }


    //@ApiOperation(value = "findDoctorById"/*, tags = SwaggerTags.DOCTOR_TAG*/)
    @GetMapping(value = "/{doctorId}")
    public ResponseEntity<?> findDoctorById(@PathVariable("doctorId")Integer doctorId)
    {
        try
        {
            DoctorViewDTO doctorViewDTO = doctorService.findDoctorById(doctorId);
            return new ResponseEntity<>(doctorViewDTO, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    //@ApiOperation(value = "insertDoctor"/*, tags = SwaggerTags.DOCTOR_TAG*/)
    @PostMapping()
    public ResponseEntity<?> insertDoctor(@RequestBody JustDoctorDTO justDoctorDTO, UsersDTO usersDTO) {
        DoctorDTO doctorDTO;
        try {
            doctorDTO = doctorService.insert(justDoctorDTO, usersDTO);
            return new ResponseEntity<>(doctorDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }


   //@ApiOperation(value = "updateDoctor"/*, tags = SwaggerTags.DOCTOR_TAG*/)
    @PutMapping(value = "/{doctorId}")
    public ResponseEntity<?>updateDoctor(@PathVariable("doctorId") Integer doctorId,
                                       @RequestBody DoctorViewDTO doctorViewDTO)
    {
        try {
            DoctorViewDTO updatedDoctor = doctorService.update(doctorId, doctorViewDTO);
            return new ResponseEntity<>(updatedDoctor, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception ex)
        {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //@ApiOperation(value = "deleteDoctor"/*, tags = SwaggerTags.DOCTOR_TAG*/)
    @DeleteMapping(value = "/{doctorId}")
    public ResponseEntity<?> deleteUser(@PathVariable("doctorId") Integer doctorId)
    {
        try {

            doctorService.delete(doctorId);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (ResourceNotFoundException rne) {
            return new ResponseEntity<>(rne.getMessage(),HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
