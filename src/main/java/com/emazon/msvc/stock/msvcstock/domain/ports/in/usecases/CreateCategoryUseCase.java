package com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases;

import com.emazon.msvc.stock.msvcstock.domain.models.Category;

public interface CreateCategoryUseCase {
  Category create(Category category);
}
