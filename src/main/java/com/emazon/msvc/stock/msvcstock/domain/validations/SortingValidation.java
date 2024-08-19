package com.emazon.msvc.stock.msvcstock.domain.validations;

import com.emazon.msvc.stock.msvcstock.domain.exceptions.sorting.InvalidSortByFieldException;

public interface SortingValidation {
  void isValidSortBy(String sortBy) throws InvalidSortByFieldException;
}
