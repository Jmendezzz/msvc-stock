package com.emazon.msvc.stock.msvcstock.domain.validations.imp;

import com.emazon.msvc.stock.msvcstock.domain.exceptions.sorting.InvalidSortByFieldException;
import com.emazon.msvc.stock.msvcstock.domain.validations.SortingValidation;

import java.util.Set;

public class BrandSortingValidation implements SortingValidation {
  private final Set<String> VALID_SORT_FIELDS = Set.of("name");
  @Override
  public void isValidSortBy(String sortBy) throws InvalidSortByFieldException {
    if (!VALID_SORT_FIELDS.contains(sortBy)) {
      throw new InvalidSortByFieldException();
    }
  }
}
