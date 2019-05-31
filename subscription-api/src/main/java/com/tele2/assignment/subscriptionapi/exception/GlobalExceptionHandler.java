package com.tele2.assignment.subscriptionapi.exception;

import java.util.Date;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
   @ExceptionHandler(ResourceNotFoundException.class)
   public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
	   CustomErrorType customErrorType = new CustomErrorType(new Date(), ex.getMessage(), request.getDescription(false));
       return new ResponseEntity<>(customErrorType, HttpStatus.NOT_FOUND);
    }

   @ExceptionHandler(ResourceConflictException.class)
   public ResponseEntity<?> ResourceConflictException(ResourceConflictException ex, WebRequest request) {
	   CustomErrorType customErrorType = new CustomErrorType(new Date(), ex.getMessage(), request.getDescription(false));
       return new ResponseEntity<>(customErrorType, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {
        CustomErrorType customErrorType = new CustomErrorType(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(customErrorType, HttpStatus.INTERNAL_SERVER_ERROR);
    }
 
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
           HttpHeaders headers, HttpStatus status, WebRequest request) {
    	CustomErrorType customErrorType = new CustomErrorType(new Date(), "Validation Failed",
               ex.getBindingResult().toString());
           return new ResponseEntity(customErrorType, HttpStatus.BAD_REQUEST);
    } 
}