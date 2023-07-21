package com.example.paymentsapp.exception;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralExceptionHandler {
   @ExceptionHandler({ChangeSetPersister.NotFoundException.class})
    public String notFoundException(){
       return "Not Found";
   }

}
