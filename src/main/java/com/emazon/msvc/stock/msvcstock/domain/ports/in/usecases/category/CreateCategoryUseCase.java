package com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.category;

import com.emazon.msvc.stock.msvcstock.domain.models.Category;

public interface CreateCategoryUseCase {
  Category create(Category category);
}
