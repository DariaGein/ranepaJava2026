package com.example.exception;

public class GlobalExceptionHandler {
}
package ru.ranepa.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}