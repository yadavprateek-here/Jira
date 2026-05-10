package com.prakar.jira.dto;

import com.prakar.jira.entity.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateTicket {
    private String title;
    private String description;
    private String userEmail;
    private Integer daysToComplete;
}
