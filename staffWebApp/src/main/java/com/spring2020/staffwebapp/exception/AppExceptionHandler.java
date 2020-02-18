package com.spring2020.staffwebapp.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class will catch all exception of the project
 * then send response to client with messages, HttpStatus,..
 */
@RestControllerAdvice
public class AppExceptionHandler
{

    private static final Logger logger = LogManager.getLogger(AppExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(value = MissingInputException.class)
    public ResponseEntity handleException(MissingInputException exception)
    {
        logger.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity handleException(ResourceNotFoundException exception)
    {
        logger.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity handleException(MethodArgumentNotValidException exception)
    {
        List<String> errorList = exception.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        logger.error(errorList);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorList);
    }

    @ResponseBody
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity handleException(ConstraintViolationException exception)
    {
        logger.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage()
                .substring(exception.getMessage().indexOf(':') + 1));
    }

}
