package com.microservicecours.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AddRoleException extends RuntimeException{

    public AddRoleException(String msg){
        super(msg);
    }
}
