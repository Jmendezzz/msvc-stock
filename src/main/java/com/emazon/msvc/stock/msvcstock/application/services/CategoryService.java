package com.emazon.msvc.stock.msvcstock.application.services;

import com.emazon.msvc.stock.msvcstock.application.dtos.category.CategoryDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.category.CreateCategoryDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.pagination.PaginationDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.sorting.SortingDto;
import com.emazon.msvc.stock.msvcstock.domain.models.Paginated;

public interface CategoryService {
  CategoryDto create(CreateCategoryDto categoryDto);
  Paginated<CategoryDto> retrieveCategories(PaginationDto pagination, SortingDto sort);
}
