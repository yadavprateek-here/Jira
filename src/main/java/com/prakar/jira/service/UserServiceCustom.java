package com.prakar.jira.service;

import com.prakar.jira.dao.UserRepository;
import com.prakar.jira.dao.UserRepositoryCustom;
import com.prakar.jira.dto.UserRegistration;
import com.prakar.jira.entity.User;
import com.prakar.jira.exception.ResourceNotFoundException;
import com.prakar.jira.util.DataMapper;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class UserServiceCustom {

    private final UserRepository userRepository;
    private final UserRepositoryCustom userRepo;
    private final DataMapper mapper;

    public UserServiceCustom(UserRepository userRepository,
                             UserRepositoryCustom userRepo,
                             DataMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.userRepo = userRepo;
    }

    public void registerUser(@Valid UserRegistration userRegistration) {
        User user = mapper.updateUserFromDto(userRegistration);
        user = userRepository.save(user);
        mapper.updateUserToDto(user,userRegistration);
    }

    public void getUser(UserRegistration userRegistration) {
        User user = userRepo.findByEmail(userRegistration.getEmail()).orElseThrow(()-> new ResourceNotFoundException(
                "User Not Found with UserName:"+userRegistration.getEmail()
        ));
        mapper.updateUserToDto(user,userRegistration);

    }
}
