package com.springboot.blog.exception;

import com.springboot.blog.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    //handle specific exception
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webrequest){
        ErrorDetails errordetails = new ErrorDetails(new Date(),exception.getMessage(),webrequest.getDescription(false));
        return new ResponseEntity<>(errordetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BlogAPIException.class)
    public ResponseEntity<ErrorDetails> BlogAPIException(BlogAPIException exception, WebRequest webrequest){
        ErrorDetails errordetails = new ErrorDetails(new Date(),exception.getMessage(),webrequest.getDescription(false));
        return new ResponseEntity<>(errordetails, HttpStatus.BAD_REQUEST);
    }

    // Handling global exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(Exception exception, WebRequest webrequest){
        ErrorDetails errordetails = new ErrorDetails(new Date(),exception.getMessage(),webrequest.getDescription(false));
        return new ResponseEntity<>(errordetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
