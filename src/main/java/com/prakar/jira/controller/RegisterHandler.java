package com.prakar.jira.controller;

import com.prakar.jira.dto.UserRegistration;
import com.prakar.jira.response.ApiResponse;
import com.prakar.jira.service.UserServiceCustom;
import com.prakar.jira.util.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        userServiceCustom.registerUser(user);
        return ResponseUtil.buildResponse(user,request, HttpStatus.CREATED);
    }

    @GetMapping(path = "/finduser")
    ResponseEntity<ApiResponse<Object>>
    getUser ( @RequestBody UserRegistration user,
               HttpServletRequest request){
        userServiceCustom.getUser(user);
        return ResponseUtil.buildResponse(user,request, HttpStatus.CREATED);
    }
}
