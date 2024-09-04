package com.emazon.msvc.stock.msvcstock.domain.utils.constants.pagination;

public class PaginationValidationMessage {
  public static final String INVALID_PAGE_NUMBER = "Page number must be greater than or equal to 0";
  public static final String INVALID_PAGE_SIZE = "Page size must be greater than or equal to 1 and less than or equal to 100";
  private PaginationValidationMessage() {
  }
}
