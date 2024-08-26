package com.emazon.msvc.stock.msvcstock.domain.exceptions.pagination;

public enum PaginationExceptionCode {

  INVALID_PAGE_NUMBER("P001", "Page number must be greater or equal than 0"),
  NULL_PAGE_NUMBER("P002", "Page number must not be null"),
  INVALID_PAGE_SIZE("P003", "Page size must be greater than 0"),
  NULL_PAGE_SIZE("P004", "Page size must not be null")
  ;
  private final String code;

  private final String message;

  private PaginationExceptionCode(String code,String message) {
    this.code = code;
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
  public String getCode() {
    return code;
  }
}
