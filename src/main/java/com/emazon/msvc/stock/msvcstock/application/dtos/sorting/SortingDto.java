package com.emazon.msvc.stock.msvcstock.application.dtos.sorting;

public record SortingDto(
        String sortBy,
        String direction
) {

  public SortingDto {
    if (direction == null || direction.isBlank()) {
      direction = "asc";
    }
  }
}
