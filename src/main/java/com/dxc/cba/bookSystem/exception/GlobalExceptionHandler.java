package com.dxc.cba.bookSystem.exception;

/**
 * This class handles all custom exceptions of REST API's defined on CustomBookException and NoSuchBookException class
 * and all types of exceptions like 403, 500 and so on.
 */
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@ControllerAdvice

public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomBookException.class)
    public ResponseEntity<ErrorResponse> handleException(CustomBookException customEx){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        List message= new ArrayList<String>();
        message.add(customEx.getMessage());
        errorResponse.setMessages(message);
        errorResponse.setTimeStamp(new Date(System.currentTimeMillis()));
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchBookExistsException.class)
    public ResponseEntity<ErrorResponse> handleException(NoSuchBookExistsException customEx){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
        List message= new ArrayList<String>();
        message.add(customEx.getMessage());
        errorResponse.setMessages(message);
        errorResponse.setTimeStamp(new Date(System.currentTimeMillis()));
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        List list = ex.getBindingResult().getAllErrors().stream()
                .map(fieldError -> fieldError.getDefaultMessage())
                .collect(Collectors.toList());
        errorResponse.setMessages(list);
        errorResponse.setTimeStamp(new Date(System.currentTimeMillis()));
        System.out.println("method a");
        return new ResponseEntity<Object>(errorResponse,HttpStatus.BAD_REQUEST);
    }
}
