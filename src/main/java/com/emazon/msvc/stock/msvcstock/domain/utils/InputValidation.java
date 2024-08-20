package com.emazon.msvc.stock.msvcstock.domain.utils;

public class InputValidation {
  private InputValidation(){}
  public static boolean isNullOrEmpty(String input) {
    return input == null || input.isEmpty() || input.isBlank();
  }

  public static boolean isInvalidLength(String input, int min, int max) {
    return input.length() < min || input.length() > max;
  }
}
