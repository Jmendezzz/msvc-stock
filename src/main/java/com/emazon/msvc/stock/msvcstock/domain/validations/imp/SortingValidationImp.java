package com.emazon.msvc.stock.msvcstock.domain.validations.imp;

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
    String sortBy = sorting.getSortBy();

    if(InputValidation.isNullOrEmpty(sortBy)) {
      sortBy = defaultSortField;
    } else if(!validSortFields.contains(sortBy)) {
      throw new InvalidSortByFieldException();
    }

    return new Sorting(
      sortBy,
      sorting.getDirection().name()
    );
  }
}
