package com.prakar.jira.dto;

import jakarta.validation.constraints.Email;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistration {
    @NonNull
    private String userName;
    @NonNull @Email
    private String email;
    @NonNull
    private String password;
}
