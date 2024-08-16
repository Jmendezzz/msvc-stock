package com.emazon.msvc.stock.msvcstock.domain.ports.out.repositories;

import com.emazon.msvc.stock.msvcstock.domain.models.Category;

public interface CategoryRepository {
  Category save(Category category);
}
