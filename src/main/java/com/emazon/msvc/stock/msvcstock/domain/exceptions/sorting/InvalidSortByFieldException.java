package com.emazon.msvc.stock.msvcstock.domain.exceptions.sorting;

import com.emazon.msvc.stock.msvcstock.domain.exceptions.BusinessException;

public class InvalidSortByFieldException extends BusinessException{

  public InvalidSortByFieldException() {
    super(SortingExceptionCode.INVALID_SORT_BY_FIELD.getMessage(), SortingExceptionCode.INVALID_SORT_BY_FIELD.getCode());
  }
}
