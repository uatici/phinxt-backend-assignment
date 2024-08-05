package com.phinxt.homeassignment.exception;

public class ValidationErrorException extends RuntimeException {
    public ValidationErrorException(String message) {
        super(message);
    }
}
