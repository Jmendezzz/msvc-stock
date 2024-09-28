package com.emazon.msvc.stock.msvcstock.domain.validations.imp;

import com.emazon.msvc.stock.msvcstock.domain.enums.SortDirection;
import com.emazon.msvc.stock.msvcstock.domain.exceptions.sorting.InvalidSortByFieldException;
import com.emazon.msvc.stock.msvcstock.domain.models.Sorting;
import com.emazon.msvc.stock.msvcstock.domain.utils.InputValidation;
import com.emazon.msvc.stock.msvcstock.domain.validations.SortingValidation;

import java.util.Set;

public class SortingValidationImp implements SortingValidation{
  private final Set<String> validSortFields;
  private final String defaultSortField;

  public SortingValidationImp(Set<String> validSortFields, String defaultSortField) {
    this.validSortFields = validSortFields;
    this.defaultSortField = defaultSortField;
  }


  @Override
  public Sorting validateSorting(Sorting sorting) throws InvalidSortByFieldException {
    if(sorting == null) {
      return new Sorting(defaultSortField, SortDirection.ASC.name());
    }
    if(InputValidation.isNullOrEmpty(sorting.getSortBy())) {
      sorting.setSortBy(defaultSortField);
    }
    if(!validSortFields.contains(sorting.getSortBy())) {
      throw new InvalidSortByFieldException();
    }
    return sorting;
  }
}
