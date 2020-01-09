package com.example.springdemo.dto.builders.UsersBuilders;

import com.example.springdemo.dto.UsersDTOs.UsersViewDTO;
import com.example.springdemo.entities.Users;

public class UsersViewBuilder {

    private UsersViewBuilder() {
    }

    public static UsersViewDTO generateDTOFromEntity (Users user)
    {
        return new UsersViewDTO(
                user.getUsername(),
                user.getRole());
    }

    public static Users generateEntityFromDTO (UsersViewDTO usersDTO)
    {
        return new Users(
                usersDTO.getUsername(),
                usersDTO.getRole());
    }
}
