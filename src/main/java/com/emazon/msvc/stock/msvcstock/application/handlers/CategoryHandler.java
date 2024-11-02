package com.emazon.msvc.stock.msvcstock.application.handlers;

import com.emazon.msvc.stock.msvcstock.application.dtos.category.CategoryResponseDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.category.CreateCategoryRequestDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.pagination.PaginationDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.sorting.SortingDto;
import com.emazon.msvc.stock.msvcstock.domain.models.Paginated;

import java.util.List;

public interface CategoryHandler {
  CategoryResponseDto create(CreateCategoryRequestDto categoryDto);
  Paginated<CategoryResponseDto> retrieveCategories(PaginationDto pagination, SortingDto sort);
  List<CategoryResponseDto> retrieveAllCategories();
}
