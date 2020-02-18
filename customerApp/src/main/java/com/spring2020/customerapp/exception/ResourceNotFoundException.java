package com.spring2020.customerapp.exception;

/**
 * Exceptions that can be thrown when resource not found
 * ex: not found category = id
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
