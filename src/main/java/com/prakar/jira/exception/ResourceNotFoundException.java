package com.prakar.jira.exception;

public class ResourceNotFoundException extends RuntimeException{

    private String msg;

    public ResourceNotFoundException (String msg){
        this.msg = msg;
    }

    public String getMessage(){
        return msg;
    }


}
