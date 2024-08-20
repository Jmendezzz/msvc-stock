package com.emazon.msvc.stock.msvcstock.domain.exceptions.sorting;

public enum SortingExceptionCode {
    EMPTY_SORT_BY("EMPTY_SORT_BY", "Sort by field must not be empty"),
    INVALID_SORT_DIRECTION("INVALID_SORT_DIRECTION", "Sort direction must be either ASC or DESC"),
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
