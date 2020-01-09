package com.example.springdemo.dto.UsersDTOs;

import com.example.springdemo.entities.enums.UsersType;

public class UsersViewDTO {

    private String username;
    private UsersType role;

    public UsersViewDTO(String username, UsersType role) {
        this.username = username;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UsersType getRole() {
        return role;
    }

    public void setRole(UsersType role) {
        this.role = role;
    }
}
