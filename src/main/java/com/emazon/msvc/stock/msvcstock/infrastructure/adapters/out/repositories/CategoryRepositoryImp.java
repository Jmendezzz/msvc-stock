package com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.repositories;

import com.emazon.msvc.stock.msvcstock.domain.models.Category;
import com.emazon.msvc.stock.msvcstock.domain.ports.out.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class CategoryRepositoryImp implements CategoryRepository {
  private final CategoryRepository jpaRepository;

  @Override
  public Category save(Category category) {
    // TODO: Implement save method
    return null;
  }
}
