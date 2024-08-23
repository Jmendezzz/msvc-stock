package com.emazon.msvc.stock.msvcstock.application.dtos.pagination;
public record PaginationDto(
        Integer page,
        Integer size
) {
  public PaginationDto {
    if (page == null || page < 0) {
      page = 0; // default page
    }
    if (size == null || size <= 0) {
      size = 10; // default size
    }
  }
}