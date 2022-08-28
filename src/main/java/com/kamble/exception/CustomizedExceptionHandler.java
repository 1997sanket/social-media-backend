package com.kamble.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice //So that our controller sends Exceptions here
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ErrorBody> handleUserNotFoundException(Exception ex, WebRequest request) throws Exception {
        ErrorBody errorBody = new ErrorBody(
                ex.getMessage(),
                LocalDateTime.now(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String errorMessage = "";

        //because we may have multiple validation errors
        for (ObjectError allError : ex.getAllErrors()) {
            errorMessage = errorMessage + allError.getDefaultMessage() + " ";
        }


        ErrorBody errorBody = new ErrorBody(
                errorMessage,
                LocalDateTime.now(),
                request.getDescription(false)
        );

        return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
    }


        @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorBody> handleAllException(Exception ex, WebRequest request) throws Exception {
        ErrorBody errorBody = new ErrorBody(ex.getMessage(), LocalDateTime.now(), request.getDescription(false));

        return new ResponseEntity<>(errorBody, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
