package com.phinxt.homeassignment.exception;

import com.phinxt.homeassignment.dto.RunRobotResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RunRobotResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<String> errorMessages = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());
        String errorMessage = String.join(", ", errorMessages);
        return getResponse(ex, errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValidationErrorException.class)
    public ResponseEntity<RunRobotResponse> handleValidationErrorException(ValidationErrorException ex) {
        return getResponse(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InstructionNotFoundException.class)
    public ResponseEntity<RunRobotResponse> handleInstructionNotFoundException(InstructionNotFoundException ex) {
        return getResponse(ex, HttpStatus.NOT_IMPLEMENTED);
    }

    @ExceptionHandler(HttpMessageConversionException.class)
    public ResponseEntity<RunRobotResponse> handleJsonParseValidationErrorException(HttpMessageConversionException ex) {
        return getResponse(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RunRobotResponse> handleAllExceptions(Exception ex) {
        return getResponse(ex, "An unexpected error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<RunRobotResponse> getResponse(Exception ex, HttpStatus status) {
        RunRobotResponse response = new RunRobotResponse(ex.getMessage());
        log.error("Exception occurred", ex);
        return new ResponseEntity<>(response, status);
    }

    private ResponseEntity<RunRobotResponse> getResponse(Exception ex, String message, HttpStatus status) {
        RunRobotResponse response = new RunRobotResponse(message);
        log.error("Exception occurred. ErrorMessage: {}", message, ex);
        return new ResponseEntity<>(response, status);
    }
}
