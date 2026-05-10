package com.prakar.jira.exception;

public class TicketException extends RuntimeException{
    String msg;
    public TicketException(String msg){this.msg = msg;}

    public String getMessage() {
        return msg;
    }
}
