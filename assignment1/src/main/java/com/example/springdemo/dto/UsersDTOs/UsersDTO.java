package com.example.springdemo.dto.UsersDTOs;


import com.example.springdemo.entities.enums.UsersType;

import java.util.Objects;

public class UsersDTO {

    private String username;
    private String password;
    private UsersType role;

    public UsersDTO() {
    }

    public UsersDTO(String username, String password, UsersType role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UsersType getRole() {
        return role;
    }

    public void setRole(UsersType role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersDTO usersDTO = (UsersDTO) o;
        return Objects.equals(username, usersDTO.username) &&
                Objects.equals(password, usersDTO.password) &&
                role == usersDTO.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, role);
    }
}
