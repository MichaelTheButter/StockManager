package com.stockmanager.infrastructure.controllers.exceptionhandling;

public class UniqueConstraintException extends RuntimeException {
    public UniqueConstraintException(String message) {
        super(message);
    }
}
