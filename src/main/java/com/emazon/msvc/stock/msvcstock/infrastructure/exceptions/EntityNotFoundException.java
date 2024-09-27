package com.emazon.msvc.stock.msvcstock.infrastructure.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
