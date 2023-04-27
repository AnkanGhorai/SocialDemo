package com.example.socialdemo.exception;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;

@ControllerAdvice
public class CustomExceptionHandeller extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity handleAllExceptions(Exception ex, WebRequest request){
        ErrorFormat error = new ErrorFormat(ex.getMessage(),request.getDescription(false), LocalDate.now());

        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity handleUserNotFoundExceptions(Exception ex, WebRequest request){
        ErrorFormat error = new ErrorFormat(ex.getMessage(),request.getDescription(false), LocalDate.now());

        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        StringBuilder stringBuilder = new StringBuilder("Total Error Count: "+ex.getFieldErrorCount()+"\n");

        for (var error: ex.getFieldErrors()){
            stringBuilder.append(error.getDefaultMessage()+"\n");
        }

        ErrorFormat error = new ErrorFormat(stringBuilder.toString(),request.getDescription(false), LocalDate.now());
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
}
