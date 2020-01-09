package com.example.springdemo.entities;

import com.example.springdemo.entities.enums.UsersType;
import javax.persistence.*;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UsersType role;

    public Users() {
    }

    public Users(String username, String password, UsersType role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Users(String username, UsersType role) {
        this.username = username;
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
    public String toString() {
        return "Users{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
