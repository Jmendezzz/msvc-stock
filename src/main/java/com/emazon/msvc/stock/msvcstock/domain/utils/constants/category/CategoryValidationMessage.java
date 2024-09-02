package com.emazon.msvc.stock.msvcstock.domain.utils.constants.category;

public class CategoryValidationMessage {
  public static final String CATEGORY_NAME_REQUIRED = "Category name is required";
  public static final String CATEGORY_NAME_LENGTH = "Category name length must be between 3 and 50 characters";
  public static final String CATEGORY_DESCRIPTION_REQUIRED = "Category description is required";
  public static final String CATEGORY_DESCRIPTION_LENGTH = "Category description length must be between 3 and 90 characters";
  private CategoryValidationMessage() {
  }
}
