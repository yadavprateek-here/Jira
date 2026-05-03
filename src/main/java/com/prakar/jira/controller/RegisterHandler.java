package com.prakar.jira.controller;

import com.prakar.jira.dto.UserRegistration;
import com.prakar.jira.response.ApiResponse;
import com.prakar.jira.service.UserServiceCustom;
import com.prakar.jira.util.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class RegisterHandler {

    private final UserServiceCustom userServiceCustom;

    public RegisterHandler(UserServiceCustom userServiceCustom) {
        this.userServiceCustom = userServiceCustom;
    }

    @PostMapping(path="/register")
    public ResponseEntity<ApiResponse<Object>>
    register ( @Valid @RequestBody UserRegistration user,
               HttpServletRequest request){
        System.out.println("registerUser Handler: UserRegistration : "+user);
        user = userServiceCustom.registerUser(user);
        return ResponseUtil.buildResponse(user,request, HttpStatus.CREATED);
    }

    @GetMapping(path = "/finduser")
    ResponseEntity<ApiResponse<Object>>
    getUser ( @RequestBody UserRegistration user,
               HttpServletRequest request){
        user = userServiceCustom.getUser(user);
        return ResponseUtil.buildResponse(user,request, HttpStatus.OK);
    }

    @GetMapping(path = "/whoami")
    ResponseEntity<ApiResponse<Object>>
    check (
              HttpServletRequest request){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName(); // 👈 your username (email)
        return ResponseUtil.buildResponse(userServiceCustom.getUserByEmail(email),request, HttpStatus.OK);
    }
}
