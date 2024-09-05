package com.emazon.msvc.stock.msvcstock.domain.exceptions.sorting;

import com.emazon.msvc.stock.msvcstock.domain.exceptions.BusinessException;

import static com.emazon.msvc.stock.msvcstock.domain.utils.constants.sorting.SortingValidationCode.SORTING_INVALID_SORT_BY_FIELD_CODE;
import static com.emazon.msvc.stock.msvcstock.domain.utils.constants.sorting.SortingValidationMessage.SORTING_INVALID_SORT_BY_FIELD;

public class InvalidSortByFieldException extends BusinessException{

  public InvalidSortByFieldException() {
    super(SORTING_INVALID_SORT_BY_FIELD, SORTING_INVALID_SORT_BY_FIELD_CODE);
  }
}
