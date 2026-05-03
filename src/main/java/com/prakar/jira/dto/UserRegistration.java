package com.prakar.jira.dto;

import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRegistration {

    private String userName;
    @Email
    private String email;

    private String password;

    @Override
    public String toString() {
        return "UserRegistration{" +
                "userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
