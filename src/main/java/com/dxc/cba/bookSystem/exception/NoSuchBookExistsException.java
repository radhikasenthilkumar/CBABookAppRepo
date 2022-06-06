package com.dxc.cba.bookSystem.exception;

/**
 * This class is responsible for handling scenarios and throwing custom exceptions
 * like when an employee is not available in the database. This exception class should extend RuntimeException.
 */
public class NoSuchBookExistsException extends  RuntimeException{
    public NoSuchBookExistsException() {
    }

    public NoSuchBookExistsException(String message) {
        super(message);
    }

    public NoSuchBookExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchBookExistsException(Throwable cause) {
        super(cause);
    }


}
