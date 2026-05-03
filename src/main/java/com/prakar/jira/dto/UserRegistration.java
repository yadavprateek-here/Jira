package com.prakar.jira.dto;

import com.prakar.jira.util.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    @Enumerated(EnumType.STRING)
    private Role role = Role.EMPLOYEE;

    @Override
    public String toString() {
        return "UserRegistration{" +
                "userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
