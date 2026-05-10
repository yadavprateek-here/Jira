package com.prakar.jira.service;

import com.prakar.jira.aspects.LoggingAspects;
import com.prakar.jira.dao.TicketRepository;
import com.prakar.jira.dto.CreateTicket;
import com.prakar.jira.entity.Ticket;
import com.prakar.jira.entity.UserInfo;
import com.prakar.jira.exception.ResourceNotFoundException;
import com.prakar.jira.exception.TicketException;
import com.prakar.jira.util.DataMapper;
import com.prakar.jira.util.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


//@Service
//public class TicketService {
//    @Autowired
//    DataMapper mapper;
//
//    @Autowired
//    UserServiceCustom userService;
//
//    @Autowired
//    TicketRepository ticketRepo;
//
//    // 🔹 Helper method (reusable)
//    private UserInfo getCurrentUser() {
//        String email = SecurityContextHolder
//                .getContext()
//                .getAuthentication()
//                .getName();
//
//        return userService.getUser(email);
//    }
//
//
//    public Object createTicket(CreateTicket t1) {
//        Ticket t = mapper.dtoToTicket(t1);
//        t.setStatus(Status.OPEN);
//        t.setCreatedAt(LocalDateTime.now());
//        t.setAssignedTo(userService.getUser(t1.getUserEmail()));
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        t.setCreatedBy(userService.getUser(authentication.getName()));
//        t.setDueDate(LocalDateTime.now().plusDays(t1.getDaysToComplete()));
//       Ticket tic = ticketRepo.save(t);
//       System.out.println(tic);
//       return tic;
//
//    }
//
//    public Ticket viewTicketById(String Id){
//        return ticketRepo.getReferenceById(Long.valueOf(Id));
//    }
//
//    public List<Ticket> viewTicketsOnMe(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        UserInfo user = userService.getUser(authentication.getName());
//       return ticketRepo.getTicketByAssignedTo(user);
//    }
//
//    public List<Ticket> viewTicketsOnMeOpen(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        UserInfo user = userService.getUser(authentication.getName());
//        return ticketRepo.findByAssignedToAndStatusEquals(user,Status.OPEN);
//    }
//
//    public List<Ticket> viewTicketsCreatedByMeOpen(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        UserInfo user = userService.getUser(authentication.getName());
//        return ticketRepo.findByCreatedByAndStatusEquals(user,Status.OPEN);
//    }
//
//
//}

@Service
public class TicketService {

    private final DataMapper mapper;
    private final UserServiceCustom userService;
    private final TicketRepository ticketRepo;

    private static final Logger log = LoggerFactory.getLogger(TicketService.class);

    public TicketService(DataMapper mapper, UserServiceCustom userService, TicketRepository ticketRepo) {
        this.mapper = mapper;
        this.userService = userService;
        this.ticketRepo = ticketRepo;
    }

    // 🔹 Helper method (reusable)
    private UserInfo getCurrentUser() {
        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return userService.getUser(email);
    }

    public Ticket createTicket(CreateTicket dto) {

        UserInfo assignedUser = userService.getUser(dto.getUserEmail());

        if (assignedUser == null) {
            throw new RuntimeException("Assigned user not found");
        }

        // 🔥 HIERARCHY CHECK
        if (getCurrentUser().getRole().getLevel() < assignedUser.getRole().getLevel()) {
            throw new TicketException("Cannot assign ticket to higher role");
        }

        Ticket ticket = mapper.dtoToTicket(dto);

        ticket.setStatus(Status.OPEN);
        ticket.setCreatedAt(LocalDateTime.now());
        ticket.setAssignedTo(assignedUser);
        ticket.setCreatedBy(getCurrentUser());
        ticket.setDueDate(LocalDateTime.now().plusDays(dto.getDaysToComplete()));

        Ticket saved = ticketRepo.save(ticket);

        log.info("Ticket created with id: {}", saved.getId());

        return saved;
    }

    public Ticket viewTicketById(Long id) {
        return ticketRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found"));
    }

    public List<Ticket> viewTicketsOnMe() {
        return ticketRepo.getTicketByAssignedTo(getCurrentUser());
    }

    public List<Ticket> viewTicketsOnMeOpen() {
        return ticketRepo.findByAssignedToAndStatusEquals(
                getCurrentUser(), Status.OPEN
        );
    }

    public List<Ticket> viewTicketsCreatedByMeOpen() {
        return ticketRepo.findByCreatedByAndStatusEquals(
                getCurrentUser(), Status.OPEN
        );
    }
}
