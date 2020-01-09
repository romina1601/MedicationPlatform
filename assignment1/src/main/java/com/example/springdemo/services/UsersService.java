package com.example.springdemo.services;



import com.example.springdemo.dto.UsersDTOs.UsersDTO;
import com.example.springdemo.dto.UsersDTOs.UsersSecurityDTO;
import com.example.springdemo.dto.UsersDTOs.UsersViewDTO;
import com.example.springdemo.dto.builders.UsersBuilders.UsersBuilder;
import com.example.springdemo.dto.builders.UsersBuilders.UsersSecurityBuilder;
import com.example.springdemo.dto.builders.UsersBuilders.UsersViewBuilder;
import com.example.springdemo.entities.Users;
import com.example.springdemo.errorhandler.DuplicateEntryException;
import com.example.springdemo.errorhandler.ResourceNotFoundException;
import com.example.springdemo.repositories.UsersRepository;
import com.example.springdemo.validators.UsersFieldValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsersService implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Autowired
    public UsersService (UsersRepository usersRepository)
    {
        this.usersRepository = usersRepository;
    }

    public List<UsersDTO> findAll()
    {
        List<Users>users = usersRepository.findAll();
        return users.stream()
                .map(UsersBuilder::generateDTOFromEntity)
                .collect(Collectors.toList());
    }

    public UsersViewDTO findUserById (String username)
    {
        Optional<Users> user = usersRepository.findById(username);

        if(!user.isPresent())
        {
            throw new ResourceNotFoundException("User", "username", username);
        }

        return UsersViewBuilder.generateDTOFromEntity(user.get());
    }

    public UsersDTO insert(UsersDTO usersDTO) {
        UsersFieldValidator.validateInsertOrUpdate(usersDTO);

        Users user = usersRepository.findById(usersDTO.getUsername()).orElse(null);

        if(user != null)
        {
            throw new DuplicateEntryException("User", "username", usersDTO.getUsername());
        }

        Users userInserted = UsersBuilder.generateEntityFromDTO(usersDTO);

        return UsersBuilder.generateDTOFromEntity(usersRepository.save(userInserted));
    }


    public UsersDTO update (String userId, UsersDTO usersDTO) throws Exception {
        Users user = usersRepository.findById(userId).orElse(null);

        if(user == null)
        {
            throw new ResourceNotFoundException("User", "username", userId);
        }
        if(!userId.equals(usersDTO.getUsername()))
        {
            throw new Exception("Cannot change username");
        }

        UsersFieldValidator.validateInsertOrUpdate(usersDTO);

        Users userToUpdate = UsersBuilder.generateEntityFromDTO(usersDTO);

        return UsersBuilder.generateDTOFromEntity(usersRepository.save(userToUpdate));
    }

    public void delete (String username) throws Exception
    {
        Users user = usersRepository.findById(username).orElse(null);
        if (user == null) {
            throw new ResourceNotFoundException("User", "username", username);
        }
        usersRepository.deleteById(username);
    }


    @Override
    public UsersSecurityDTO loadUserByUsername(String username) {
        Optional<Users> user = usersRepository.findById(username);

        if(!user.isPresent())
        {
            throw new ResourceNotFoundException("User", "username", username);
        }

        return UsersSecurityBuilder.build(user.get());
    }
}
