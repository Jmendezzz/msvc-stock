package com.emazon.msvc.stock.msvcstock.domain.ports.usecases;

import com.emazon.msvc.stock.msvcstock.domain.models.Category;
import com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.CreateCategoryUseCase;
import com.emazon.msvc.stock.msvcstock.domain.ports.out.repositories.CategoryRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateCategoryUseCaseImp implements CreateCategoryUseCase {
  private final CategoryRepository categoryRepository;
  @Override
  public Category create(Category category) {
    // TODO: Business logic validations
    return categoryRepository.save(category);
  }
}
