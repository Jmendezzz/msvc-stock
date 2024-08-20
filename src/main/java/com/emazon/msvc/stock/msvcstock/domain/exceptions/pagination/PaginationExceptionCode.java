package com.emazon.msvc.stock.msvcstock.domain.exceptions.pagination;

public enum PaginationExceptionCode {

  INVALID_PAGE_NUMBER("P001", "Page number must be greater or equal than 0"),
  INVALID_PAGE_SIZE("P002", "Page size must be greater than 0");
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
