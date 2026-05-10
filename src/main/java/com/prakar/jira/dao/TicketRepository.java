package com.prakar.jira.dao;

import com.prakar.jira.entity.Ticket;
import com.prakar.jira.entity.UserInfo;
import com.prakar.jira.util.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket,Long> {
     List<Ticket> getTicketByAssignedTo(UserInfo user);

    List<Ticket> findByAssignedToAndStatusEquals(UserInfo user, Status status);

    List<Ticket> findByCreatedByAndStatusEquals(UserInfo user, Status status);
}
