package com.emazon.msvc.stock.msvcstock.domain.usecases.category;

import com.emazon.msvc.stock.msvcstock.domain.factories.SortingValidationFactory;
import com.emazon.msvc.stock.msvcstock.domain.models.Category;
import com.emazon.msvc.stock.msvcstock.domain.models.Paginated;
import com.emazon.msvc.stock.msvcstock.domain.models.Pagination;
import com.emazon.msvc.stock.msvcstock.domain.models.Sorting;
import com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.category.RetrieveCategoryUseCase;
import com.emazon.msvc.stock.msvcstock.domain.ports.out.repositories.CategoryRepository;
import com.emazon.msvc.stock.msvcstock.domain.validations.SortingValidation;

import java.util.Set;

public class RetrieveCategoryUseCaseImp implements RetrieveCategoryUseCase {
  private final CategoryRepository categoryRepository;
  private final SortingValidation categorySortingValidation;

  public RetrieveCategoryUseCaseImp(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
    this.categorySortingValidation = SortingValidationFactory.getSortingValidation(Category.class);
  }

  @Override
  public Paginated<Category> retrieveCategories(Pagination pagination, Sorting sort) {
    categorySortingValidation.isValidSortBy(sort.getSortBy());

    return categoryRepository.findAll(pagination, sort);
  }

  @Override
  public Set<Category> retrieveCategoriesByIds(Set<Long> ids) {
    return categoryRepository.findAllById(ids);
  }

}
