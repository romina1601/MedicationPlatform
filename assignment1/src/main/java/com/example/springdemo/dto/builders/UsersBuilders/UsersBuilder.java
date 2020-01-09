package com.example.springdemo.dto.builders.UsersBuilders;

import com.example.springdemo.dto.UsersDTOs.UsersDTO;
import com.example.springdemo.entities.Users;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UsersBuilder {

    private UsersBuilder() {
    }

    public static UsersDTO generateDTOFromEntity (Users user)
    {
        return new UsersDTO(
                user.getUsername(),
                user.getPassword(),
                user.getRole());
    }

    public static Users generateEntityFromDTO (UsersDTO usersDTO)
    {
        return new Users(
                usersDTO.getUsername(),
                encodePassword(usersDTO.getPassword()),
                usersDTO.getRole());
    }

    public static String encodePassword(String password)
    {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}
