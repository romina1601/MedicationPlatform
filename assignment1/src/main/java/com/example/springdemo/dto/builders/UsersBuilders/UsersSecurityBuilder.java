package com.example.springdemo.dto.builders.UsersBuilders;

import com.example.springdemo.dto.UsersDTOs.UsersSecurityDTO;
import com.example.springdemo.entities.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public class UsersSecurityBuilder {

    public UsersSecurityBuilder() {
    }

    public static UsersSecurityDTO build(Users user) {
        List<GrantedAuthority> authority = new ArrayList<GrantedAuthority>();
        authority.add(new SimpleGrantedAuthority(user.getRole().toString()));

        return new UsersSecurityDTO(
                user.getUsername(),
                user.getPassword(),
                user.getRole(),
                authority
        );
    }
}
