package com.emazon.msvc.stock.msvcstock.application.validations;

import java.util.Set;

public class CategorySortingValidation implements SortingValidation {
  private final Set<String> VALID_SORT_FIELDS = Set.of("name");

  @Override
  public boolean isValidSortBy(String sortBy) {
    return VALID_SORT_FIELDS.contains(sortBy);
  }


}