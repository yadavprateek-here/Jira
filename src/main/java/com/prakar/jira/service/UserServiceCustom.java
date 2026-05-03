package com.prakar.jira.service;

import com.prakar.jira.dao.UserRepository;
import com.prakar.jira.dao.UserRepositoryCustom;
import com.prakar.jira.dto.UserRegistration;
import com.prakar.jira.entity.User;
import com.prakar.jira.entity.UserInfo;
import com.prakar.jira.exception.ResourceNotFoundException;
import com.prakar.jira.util.DataMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceCustom {


    private final UserRepository userRepository;
    private final UserRepositoryCustom userRepo;
    private final DataMapper mapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserServiceCustom(UserRepository userRepository,
                             UserRepositoryCustom userRepo,
                             DataMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.userRepo = userRepo;
    }

    public UserRegistration registerUser(@Valid UserRegistration dto) {
        User user = mapper.userRegistrationToUser(dto);
        UserInfo userInfo = mapper.userRegistrationToUserInfo(dto);

        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        // save
        userRepository.save(user);
        userInfo = userRepo.save(userInfo);

        // return safe response
        dto = mapper.toDto(userInfo);
        dto.setPassword("");
        return dto;
    }

    public UserRegistration getUser(UserRegistration dto) {
        UserRegistration finalDto = dto;
        UserInfo userInfo = userRepo.findByEmail(dto.getEmail()).orElseThrow(()-> new ResourceNotFoundException(
                "User Not Found with UserName:"+ finalDto.getEmail()
        ));
        dto = mapper.toDto(userInfo);
        System.out.println("user:"+userInfo  +"///getUser: " + dto);
        return dto;

    }

    public UserRegistration getUserByEmail(String email) {
        UserInfo userInfo = userRepo.findByEmail(email).orElseThrow( ()-> new ResourceNotFoundException("User Not Found"));
        return mapper.toDto(userInfo);
    }
}
