package com.example.springdemo.controller;


//import com.example.springdemo.configuration.SwaggerTags;
import com.example.springdemo.repositories.UsersRepository;
//import com.example.springdemo.security.JwtProvider;
//import com.example.springdemo.security.JwtResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@CrossOrigin
public class LoginController {

    /*@Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtProvider jwtProvider;*/



    /*@ApiOperation(value = "login", tags = SwaggerTags.LOGIN_TAG)
    @PostMapping("/login")
    public ResponseEntity<?> loginUser( String username, String password) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }*/

    @RequestMapping(value = "login", method = RequestMethod.GET)
    @ResponseBody
    public Principal currentUserName(Principal principal) {
        System.out.println(principal.toString());
        return principal;
    }

}
