package com.emazon.msvc.stock.msvcstock.application.sorting;

import java.util.Set;

public class CategorySortingStrategy implements SortingStrategy {
  private final Set<String> VALID_SORT_FIELDS = Set.of("name", "description");
  private final Set<String> VALID_DIRECTIONS = Set.of("ASC", "DESC");


  @Override
  public boolean isValidSortBy(String sortBy) {
    return VALID_SORT_FIELDS.contains(sortBy);
  }

  @Override
  public boolean isValidDirection(String direction) {
    return VALID_DIRECTIONS.contains(direction);
  }
}