package com.svetikov.demo000.controller;

import com.svetikov.demo000.exeption.CustomerNotFoundException;
import com.svetikov.demo000.model.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomizeResponseEntityExceptinHandler extends ResponseEntityExceptionHandler {
@ExceptionHandler(CustomerNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleUserNotFoundException(CustomerNotFoundException ex,
                                                                         WebRequest request){

ErrorDetails errorDetails=new ErrorDetails(new Date(),ex.getMessage(),request.getDescription(false));
return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
}
}
