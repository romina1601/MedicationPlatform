package com.example.springdemo.validators;


import com.example.springdemo.dto.UsersDTOs.UsersDTO;
import com.example.springdemo.errorhandler.IncorrectParameterException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

public class UsersFieldValidator {

    private static final Log LOGGER = LogFactory.getLog(UsersFieldValidator.class);

    private UsersFieldValidator() {
    }

    public static void validateInsertOrUpdate(UsersDTO usersDTO)
    {
        List<String> errors = new ArrayList<>();
        if (usersDTO == null) {
            errors.add("userDTO is null");
            throw new IncorrectParameterException(UsersDTO.class.getSimpleName(), errors);
        }

    }
}
