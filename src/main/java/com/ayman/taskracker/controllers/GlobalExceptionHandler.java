package com.ayman.taskracker.controllers;

import com.ayman.taskracker.domain.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
/**
 * GlobalExceptionHandler is a centralized exception handling class for the application.
 * It uses the @ControllerAdvice annotation to handle exceptions across the whole application
 * in a consistent manner.
 */
@ControllerAdvice
public class GlobalExceptionHandler
{
    /**
     * Handles IllegalArgumentException and other RuntimeExceptions thrown in the application.
     *
     * @param exception the exception that was thrown
     * @param request the web request during which the exception occurred
     * @return a ResponseEntity containing an ErrorResponse with details about the error
     */
    @ExceptionHandler (exception = IllegalArgumentException.class)
    public ResponseEntity <ErrorResponse> handleExceptions (RuntimeException exception , WebRequest request)
    {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
