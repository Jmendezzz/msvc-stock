package com.emazon.msvc.stock.msvcstock.domain.ports.out.repositories;

import com.emazon.msvc.stock.msvcstock.domain.models.Category;

import java.util.Optional;

public interface CategoryRepository {
  Category save(Category category);
  Optional<Category> findByName(String name);
}
