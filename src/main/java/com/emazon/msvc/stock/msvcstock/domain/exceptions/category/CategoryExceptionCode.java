package com.emazon.msvc.stock.msvcstock.domain.exceptions.category;

public enum CategoryExceptionCode {
  EMPTY_NAME("CI01", "Category name cannot be empty."),
  INVALID_NAME_LENGTH("CI02", "Category name must be between 3 and 50 characters."),
  EMPTY_DESCRIPTION("CI03", "Category description cannot be empty."),
  INVALID_DESCRIPTION_LENGTH("CI04", "Category description must be between 3 and 90 characters."),
  DUPLICATE_NAME("C001", "Category name already exists."),
  NOT_FOUND("C002", "Category not found.");

  private final String code;
  private final String message;

  CategoryExceptionCode(String code, String message) {
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
