package com.microservicecours.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class AddCategoryException extends RuntimeException{

    public AddCategoryException(String msg){
        super(msg);
    }
}
