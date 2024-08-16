package com.emazon.msvc.stock.msvcstock.application.services;

import com.emazon.msvc.stock.msvcstock.application.dtos.category.CategoryDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.category.CreateCategoryDto;
import com.emazon.msvc.stock.msvcstock.application.mappers.CategoryMapper;
import com.emazon.msvc.stock.msvcstock.application.services.imp.CategoryService;
import com.emazon.msvc.stock.msvcstock.domain.models.Category;
import com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.CreateCategoryUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CategoryServiceImp implements CategoryService {
  private final CreateCategoryUseCase createCategoryUseCase;
  private final CategoryMapper mapper;


  @Override
  public CategoryDto create(CreateCategoryDto categoryDto) {
    Category categoryCreated = createCategoryUseCase.create(mapper.toDomain(categoryDto));

    return mapper.toDto(categoryCreated);
  }
}
