package com.emazon.msvc.stock.msvcstock.application.sorting;

public interface SortingStrategy {
  boolean isValidSortBy(String sortBy);
  boolean isValidDirection(String direction);
}
