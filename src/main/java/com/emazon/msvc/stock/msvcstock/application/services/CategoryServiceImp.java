package com.emazon.msvc.stock.msvcstock.application.services;

import com.emazon.msvc.stock.msvcstock.application.dtos.category.CategoryDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.category.CreateCategoryDto;
import com.emazon.msvc.stock.msvcstock.application.mappers.CategoryMapper;
import com.emazon.msvc.stock.msvcstock.application.services.imp.CategoryService;
import com.emazon.msvc.stock.msvcstock.application.sorting.CategorySortingStrategy;
import com.emazon.msvc.stock.msvcstock.application.sorting.SortingStrategy;
import com.emazon.msvc.stock.msvcstock.domain.models.Category;
import com.emazon.msvc.stock.msvcstock.domain.models.Paginated;
import com.emazon.msvc.stock.msvcstock.domain.models.Pagination;
import com.emazon.msvc.stock.msvcstock.domain.models.Sorting;
import com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.CreateCategoryUseCase;
import com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.RetrieveCategoryUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CategoryServiceImp implements CategoryService {
  private final CreateCategoryUseCase createCategoryUseCase;
  private final RetrieveCategoryUseCase retrieveCategoriesUseCase;
  private final CategoryMapper mapper;
  private final SortingStrategy sortingStrategy = new CategorySortingStrategy();

  @Override
  public CategoryDto create(CreateCategoryDto categoryDto) {
    Category categoryCreated = createCategoryUseCase.create(mapper.toDomain(categoryDto));

    return mapper.toDto(categoryCreated);
  }

  @Override
  public Paginated<CategoryDto> retrieveCategories(Pagination pagination, Sorting sort) {
    if(!sortingStrategy.isValidSortBy(sort.getField())) {
      throw new IllegalArgumentException("Invalid sort field");
    }
    if(!sortingStrategy.isValidDirection(sort.getDirection().name())) {
      throw new IllegalArgumentException("Invalid sort direction");
    }
    Paginated<Category> categories = retrieveCategoriesUseCase.retrieveCategories(pagination, sort);

    return mapper.toDtoPaginated(categories);
  }
}
