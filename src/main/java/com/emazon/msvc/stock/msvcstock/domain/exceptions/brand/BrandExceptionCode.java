package com.emazon.msvc.stock.msvcstock.domain.exceptions.brand;

public enum BrandExceptionCode {
  EMPTY_NAME("BI01","Brand name cannot be empty"),
  INVALID_NAME_LENGTH("BI02","Brand name must be between 3 and 50 characters"),
  EMPTY_DESCRIPTION("BI03","Brand description cannot be empty"),
  INVALID_DESCRIPTION_LENGTH("BI04","Brand description must be between 3 and 120 characters"),

  BRAND_ALREADY_EXISTS("B001","Brand already exists"),
  BRAND_NOT_FOUND("B002","Brand not found");
  private final String code;
  private final String message;
  BrandExceptionCode(String code,String message) {
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
