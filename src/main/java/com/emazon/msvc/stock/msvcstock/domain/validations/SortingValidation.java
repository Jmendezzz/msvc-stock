package com.emazon.msvc.stock.msvcstock.domain.validations;

import com.emazon.msvc.stock.msvcstock.domain.exceptions.sorting.InvalidSortByFieldException;
import com.emazon.msvc.stock.msvcstock.domain.models.Sorting;

public interface SortingValidation {
  Sorting validateSorting(Sorting sorting) throws InvalidSortByFieldException;
}
