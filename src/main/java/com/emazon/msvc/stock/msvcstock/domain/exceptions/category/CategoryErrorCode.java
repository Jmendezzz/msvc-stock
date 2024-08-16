package com.emazon.msvc.stock.msvcstock.domain.exceptions.category;

public enum CategoryErrorCode {
  DUPLICATE_NAME("C001", "Category name already exists."),
  NOT_FOUND("C002", "Category not found.");

  private final String code;
  private final String message;

  CategoryErrorCode(String code, String message) {
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
