package com.emazon.msvc.stock.msvcstock.application.handlers.imp;

import com.emazon.msvc.stock.msvcstock.application.dtos.category.CategoryDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.category.CreateCategoryDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.pagination.PaginationDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.sorting.SortingDto;
import com.emazon.msvc.stock.msvcstock.application.mappers.CategoryMapper;
import com.emazon.msvc.stock.msvcstock.application.mappers.PaginationMapper;
import com.emazon.msvc.stock.msvcstock.application.mappers.SortingMapper;
import com.emazon.msvc.stock.msvcstock.application.handlers.CategoryHandler;
import com.emazon.msvc.stock.msvcstock.domain.models.Category;
import com.emazon.msvc.stock.msvcstock.domain.models.Paginated;
import com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.category.CreateCategoryUseCase;
import com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.category.RetrieveCategoryUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CategoryHandlerImp implements CategoryHandler {
  private final CreateCategoryUseCase createCategoryUseCase;
  private final RetrieveCategoryUseCase retrieveCategoriesUseCase;
  private final CategoryMapper mapper;
  private final PaginationMapper paginationMapper;
  private final SortingMapper sortingMapper;

  @Override
  public CategoryDto create(CreateCategoryDto categoryDto) {
    Category categoryCreated = createCategoryUseCase.create(mapper.toDomain(categoryDto));

    return mapper.toDto(categoryCreated);
  }

  @Override
  public Paginated<CategoryDto> retrieveCategories(PaginationDto pagination, SortingDto sort) {
    Paginated<Category> categories = retrieveCategoriesUseCase
            .retrieveCategories(
                    paginationMapper.toDomain(pagination),
                    sortingMapper.toDomain(sort)
            );

    return mapper.toDtoPaginated(categories);
  }
}