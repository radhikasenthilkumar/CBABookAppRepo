package com.dxc.cba.bookSystem.exception;

/**
 * This class is responsible for handling scenarios and throwing custom exceptions
 * like when an employee is not available in the database or
 *  some validation exception for mandatory fields.
 *  This exception class should extend RuntimeException.
 */
public class CustomBookException extends  RuntimeException{
    public CustomBookException() {
    }

    public CustomBookException(String message) {
        super(message);
    }

    public CustomBookException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomBookException(Throwable cause) {
        super(cause);
    }


}
