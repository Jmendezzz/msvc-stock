package com.emazon.msvc.stock.msvcstock.application.mappers;

import com.emazon.msvc.stock.msvcstock.application.dtos.category.CategoryDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.category.CreateCategoryDto;
import com.emazon.msvc.stock.msvcstock.domain.models.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
  Category toDomain(CreateCategoryDto createCategoryDto);
  CategoryDto toDto(Category category);
}
