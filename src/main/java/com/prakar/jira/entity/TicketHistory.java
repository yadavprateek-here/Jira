package com.prakar.jira.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class TicketHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Ticket ticket;

    private String fieldName;

    private String oldValue;

    private String newValue;

    @ManyToOne
    private UserInfo changedBy;

    private LocalDateTime changedAt;
}