package com.example.demo;

import java.util.ArrayList;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class GenericExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(Exception.class)
    public final ResponseEntity<String> genericHandler(Exception ex, WebRequest req) {
        return new ResponseEntity<String>("An error occurred. Please contact support or try again.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handle(ConstraintViolationException exception) {
        String errorMessage = new ArrayList<>(exception.getConstraintViolations()).get(0).getMessage();
        return new ResponseEntity<String>(errorMessage, null, HttpStatus.BAD_REQUEST);
    }
}
