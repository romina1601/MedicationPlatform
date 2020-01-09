package com.example.springdemo.dto.builders.UsersBuilders;

import com.example.springdemo.dto.UsersDTOs.UsersDTOWithUsername;
import com.example.springdemo.entities.Users;

public class UsersWithUsernameBuilder {

    private UsersWithUsernameBuilder() {
    }


    public static UsersDTOWithUsername generateDTOFromEntity (Users user)
    {
        return new UsersDTOWithUsername(user.getUsername());
    }
}
