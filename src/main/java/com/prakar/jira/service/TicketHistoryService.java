package com.prakar.jira.service;

import com.prakar.jira.dao.TicketHistoryRepository;
import com.prakar.jira.entity.Ticket;
import com.prakar.jira.entity.TicketHistory;
import com.prakar.jira.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketHistoryService {

    private final TicketHistoryRepository historyRepository;
    //private final TicketService
    public TicketHistoryService(TicketHistoryRepository historyRepository){
        this.historyRepository = historyRepository;
    }

   public TicketHistory saveTicketHistory(TicketHistory ticketHistory){
        return historyRepository.save(ticketHistory);
    }
    public List<TicketHistory> getTicketHistory(Long ticketId){
        Ticket t= new Ticket();
        t.setId(ticketId);
        return historyRepository.findByTicket(t).orElseThrow(()-> new ResourceNotFoundException("Ticket not Found"));
    }

}
