package com.emazon.msvc.stock.msvcstock.domain.exceptions.sorting;

public enum SortingExceptionCode {
    INVALID_SORT_BY_FIELD("INVALID_SORT_BY_FIELD", "Invalid sort by field");

    private final String code;
    private final String message;

    SortingExceptionCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
