package com.emazon.msvc.stock.msvcstock.domain.exceptions;

import java.util.Map;

public class InvalidInputsException extends RuntimeException{
  private final Map<String, String> errors;
  public InvalidInputsException(Map<String, String> errors) {
    super("Invalid inputs: " + errors.toString());
    this.errors = errors;
  }
  public Map<String, String> getErrors() {
    return errors;
  }
}
