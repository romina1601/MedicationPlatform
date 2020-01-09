package com.example.springdemo.controller;


import com.example.springdemo.dto.UsersDTOs.UsersDTO;
import com.example.springdemo.dto.UsersDTOs.UsersViewDTO;
import com.example.springdemo.errorhandler.ResourceNotFoundException;
import com.example.springdemo.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/users")
public class UsersController {

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }


    //@ApiOperation(value = "findAllUsers"/*, tags = SwaggerTags.USERS_TAG*/)
    @GetMapping()
    public List<UsersDTO> findAllUsers() {
        return usersService.findAll();
    }

    //@ApiOperation(value = "findUserById"/*, tags = SwaggerTags.USERS_TAG*/)
    @GetMapping(value = "/{username}")
    public ResponseEntity<?> findUserById(@PathVariable("username")String username)
    {
        try
        {
            UsersViewDTO usersViewDTO = usersService.findUserById(username);
            return new ResponseEntity<>(usersViewDTO, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    //@ApiOperation(value = "insertUser"/*, tags = SwaggerTags.USERS_TAG*/)
    @PostMapping()
    public ResponseEntity<?> insertUser(@RequestBody UsersDTO usersDTO) {
        UsersDTO usersDTOToBeInserted;
        try {
            usersDTOToBeInserted = usersService.insert(usersDTO);
            return new ResponseEntity<>(usersDTOToBeInserted, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

   // @ApiOperation(value = "updateUser"/*, tags = SwaggerTags.USERS_TAG*/)
    @PutMapping(value = "/{userId}")
    public ResponseEntity<?>updateUser(@PathVariable("userId") String username,
                                       @RequestBody UsersDTO usersDTO)
    {
        try {
            UsersDTO updatedUser = usersService.update(username, usersDTO);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception ex)
        {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


   // @ApiOperation(value = "deleteUser"/*, tags = SwaggerTags.USERS_TAG*/)
    @DeleteMapping(value = "/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable("username") String username)
    {
        try {
            usersService.delete(username);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (ResourceNotFoundException rne) {
            return new ResponseEntity<>(rne.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
