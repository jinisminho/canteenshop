package com.spring2020.customerapp.exception;

/**
 * Exceptions that can be thrown when input is Null
 */
public class MissingInputException extends RuntimeException {

    public MissingInputException(String message) {
        super(message);
    }
}
