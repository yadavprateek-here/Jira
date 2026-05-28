package com.prakar.jira.controller;

import com.prakar.jira.dto.AuthRequest;
import com.prakar.jira.filter.JWT.JwtUtil;
import com.prakar.jira.response.ApiResponse;
import com.prakar.jira.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest request) {

       // System.out.println(request.getUsername()+request.getPassword());
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        String token = jwtUtil.generateToken(request.getUsername());
        ResponseCookie cookie = ResponseCookie.from("jwt", token)
                .httpOnly(true)     // 🔥 prevents JS access
                //.secure(true)       // only HTTPS
                .path("/")
                .maxAge(60 * 60)
                .sameSite("Strict")
                .build();

        //ResponseUtil.buildResponse("Login successful",request, HttpStatus.OK);
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body("Login successful");

//        return jwtUtil.generateToken(request.getUsername());
    }
}
