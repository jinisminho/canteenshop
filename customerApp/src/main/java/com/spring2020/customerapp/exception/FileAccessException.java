package com.spring2020.customerapp.exception;

public class FileAccessException extends RuntimeException {

    public FileAccessException(String message) {
        super(message);
    }
}
