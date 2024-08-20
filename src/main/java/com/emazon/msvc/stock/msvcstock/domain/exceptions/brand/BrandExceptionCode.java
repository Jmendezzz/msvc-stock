package com.emazon.msvc.stock.msvcstock.domain.exceptions.brand;

public enum BrandExceptionCode {
  BRAND_ALREADY_EXISTS("","Brand already exists"),
  BRAND_NOT_FOUND("","Brand not found");
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
