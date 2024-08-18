package com.emazon.msvc.stock.msvcstock.application.services.imp;

import com.emazon.msvc.stock.msvcstock.application.dtos.category.CategoryDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.category.CreateCategoryDto;
import com.emazon.msvc.stock.msvcstock.domain.models.Paginated;
import com.emazon.msvc.stock.msvcstock.domain.models.Pagination;
import com.emazon.msvc.stock.msvcstock.domain.models.Sorting;

public interface CategoryService {
  CategoryDto create(CreateCategoryDto categoryDto);
  Paginated<CategoryDto> retrieveCategories(Pagination pagination, Sorting sort);
}
