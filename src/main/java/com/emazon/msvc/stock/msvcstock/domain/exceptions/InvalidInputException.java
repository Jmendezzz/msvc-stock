package com.emazon.msvc.stock.msvcstock.domain.exceptions;

public class InvalidInputException extends BusinessException{

  public InvalidInputException(String message, String code) {
    super(code, message);
  }
}
