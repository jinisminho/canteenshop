package com.spring2020.customerapp.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
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
public class AppExceptionHandler {

    private static final Logger logger = LogManager.getLogger(AppExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(value = MissingInputException.class)
    public ResponseEntity handleException(MissingInputException exception) {
        logger.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity handleException(ResourceNotFoundException exception) {
        logger.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity handleException(MethodArgumentNotValidException exception) {
        List<String> errorList = exception.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        logger.error(errorList);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorList);
    }

    @ResponseBody
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity handleException(ConstraintViolationException exception) {
        logger.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage()
                .substring(exception.getMessage().indexOf(':') + 1));
    }

    @ResponseBody
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity handleException(HttpMessageNotReadableException exception) {
        logger.error(exception.getMessage());
        if (exception.getMessage() != null
                && exception.getMessage().contains("java.time.LocalDate")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid date must has format: yyyy-mm-dd");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }


    @ResponseBody
    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity handleException(DataIntegrityViolationException exception) {
        logger.error(exception.getMessage());
        if (exception.getRootCause() != null && exception.getRootCause().getMessage() != null
                && exception.getRootCause().getMessage().contains("FK_Product_Category")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot delete this category");
        }
        if (exception.getRootCause() != null && exception.getRootCause().getMessage() != null
                && exception.getRootCause().getMessage().contains("UK_AppUser_UserName")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username must be unique");
        }
        if (exception.getRootCause() != null && exception.getRootCause().getMessage() != null
                && exception.getRootCause().getMessage().contains("UK_Staff_SocialId")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Social ID must be unique");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = CommonException.class)
    public ResponseEntity handleException(CommonException exception) {
        logger.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
    }
}
