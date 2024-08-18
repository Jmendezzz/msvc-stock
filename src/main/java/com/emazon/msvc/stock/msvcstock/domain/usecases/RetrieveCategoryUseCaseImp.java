package com.emazon.msvc.stock.msvcstock.domain.usecases;

import com.emazon.msvc.stock.msvcstock.domain.models.Category;
import com.emazon.msvc.stock.msvcstock.domain.models.Paginated;
import com.emazon.msvc.stock.msvcstock.domain.models.Pagination;
import com.emazon.msvc.stock.msvcstock.domain.models.Sorting;
import com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.RetrieveCategoryUseCase;
import com.emazon.msvc.stock.msvcstock.domain.ports.out.repositories.CategoryRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RetrieveCategoryUseCaseImp implements RetrieveCategoryUseCase {
  private final CategoryRepository categoryRepository;

  @Override
  public Paginated<Category> retrieveCategories(Pagination pagination, Sorting sort) {
    return categoryRepository.findAll(pagination, sort);
  }

}
