package com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.mappers;

import com.emazon.msvc.stock.msvcstock.domain.models.Category;
import com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.entities.CategoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DboCategoryMapper {
  Category toDomain(CategoryEntity categoryEntity);
  CategoryEntity toEntity(Category category);
}
