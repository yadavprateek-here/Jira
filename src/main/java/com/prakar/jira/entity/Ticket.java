package com.prakar.jira.entity;

import com.prakar.jira.util.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    private UserInfo assignedTo;

    @ManyToOne
    private UserInfo createdBy;

    // 🔥 TIME FIELDS
    private LocalDateTime createdAt;
    private LocalDateTime dueDate;
    private LocalDateTime completedAt;

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", assignedTo=" + assignedTo +
                ", createdBy=" + createdBy +
                ", createdAt=" + createdAt +
                ", dueDate=" + dueDate +
                ", completedAt=" + completedAt +
                '}';
    }
}