package com.emazon.msvc.stock.msvcstock.domain.ports.out.repositories;

import com.emazon.msvc.stock.msvcstock.domain.models.Category;
import com.emazon.msvc.stock.msvcstock.domain.models.Paginated;
import com.emazon.msvc.stock.msvcstock.domain.models.Pagination;
import com.emazon.msvc.stock.msvcstock.domain.models.Sorting;

import java.util.Optional;
import java.util.Set;

public interface CategoryRepository {
  Category save(Category category);
  Optional<Category> findByName(String name);
  Paginated<Category> findAll(Pagination pagination, Sorting sort);
  Set<Category> findAllById(Set<Long> ids);
}
