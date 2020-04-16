package com.microservicecours.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AddCourseException extends RuntimeException{

    public AddCourseException(String msg){ super(msg);}
}
