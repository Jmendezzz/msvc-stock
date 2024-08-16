package com.emazon.msvc.stock.msvcstock.domain.exceptions;

public class BusinessException extends RuntimeException{
    private final String code;

    public BusinessException(String message, String code) {
        super(message);
        this.code = code;
    }
    public String getCode() {
        return code;
    }
}
