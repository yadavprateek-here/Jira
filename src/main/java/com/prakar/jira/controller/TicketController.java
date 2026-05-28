package com.prakar.jira.controller;

import com.prakar.jira.dto.CreateTicket;
import com.prakar.jira.entity.Ticket;
import com.prakar.jira.response.ApiResponse;
import com.prakar.jira.service.TicketService;

import com.prakar.jira.util.ResponseUtil;
import com.prakar.jira.util.Status;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;


    @PostMapping("/createTicket")
    public ResponseEntity<ApiResponse<Object>> createTicket(@RequestBody CreateTicket t1, HttpServletRequest request){
        return ResponseUtil.buildResponse(ticketService.createTicket(t1),request, HttpStatus.OK);

    }

    @GetMapping("/getTicketById")
    public ResponseEntity<ApiResponse<Object>> getTicketById(@RequestParam Long Id, HttpServletRequest request){
        return  ResponseUtil.buildResponse(ticketService.viewTicketById(Id),request,HttpStatus.OK);

    }

    @GetMapping("/viewTicketsOnMe")
    public ResponseEntity<ApiResponse<Object>> viewTicketsOnMe( HttpServletRequest request){
        return ResponseUtil.buildResponse(ticketService.viewTicketsOnMe(),request,HttpStatus.OK);
    }
    @GetMapping("/viewTicketsOnMeOpen")
    public ResponseEntity<ApiResponse<Object>>viewTicketsOnMeOpen( HttpServletRequest request){
        return ResponseUtil.buildResponse(ticketService.viewTicketsOnMeOpen(),request,HttpStatus.OK);
    }
    @GetMapping("/viewTicketsCreatedByMeOpen")
    public ResponseEntity<ApiResponse<Object>> viewTicketsCreatedByMeOpen( HttpServletRequest request){
        return ResponseUtil.buildResponse(ticketService.viewTicketsCreatedByMeOpen(),request,HttpStatus.OK);
    }

    @PutMapping("/updateTicket")
    public ResponseEntity<ApiResponse<Object>> updateStatus(@RequestParam Long ticketId, @RequestParam Status newStatus,HttpServletRequest request){
        return ResponseUtil.buildResponse(ticketService.updateStatus(ticketId,newStatus),request,HttpStatus.OK);
    }
    @GetMapping("/viewTicketHistory")
    public ResponseEntity<ApiResponse<Object>> viewTicketHistory( @RequestParam Long ticketId,HttpServletRequest request){
        return ResponseUtil.buildResponse(ticketService.viewTicketHistory(ticketId),request,HttpStatus.OK);
    }



}
