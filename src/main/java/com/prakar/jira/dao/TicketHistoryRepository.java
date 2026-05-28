package com.prakar.jira.dao;

import com.prakar.jira.entity.Ticket;
import com.prakar.jira.entity.TicketHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TicketHistoryRepository extends JpaRepository<TicketHistory,Long> {
    Optional<List<TicketHistory>> findByTicket(Ticket t);
}
