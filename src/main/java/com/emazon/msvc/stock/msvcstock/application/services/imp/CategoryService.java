package com.emazon.msvc.stock.msvcstock.application.services.imp;

import com.emazon.msvc.stock.msvcstock.application.dtos.category.CategoryDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.category.CreateCategoryDto;

public interface CategoryService {

  CategoryDto create(CreateCategoryDto categoryDto);
}
