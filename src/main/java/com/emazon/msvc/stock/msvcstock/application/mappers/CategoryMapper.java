package com.emazon.msvc.stock.msvcstock.application.mappers;

import com.emazon.msvc.stock.msvcstock.application.dtos.category.CategoryResponseDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.category.CreateCategoryRequestDto;
import com.emazon.msvc.stock.msvcstock.domain.models.Category;
import com.emazon.msvc.stock.msvcstock.domain.models.Paginated;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
  Category toDomain(CreateCategoryRequestDto createCategoryRequestDto);
  CategoryResponseDto toDto(Category category);
  List<CategoryResponseDto> toDto(List<Category> categories);
  @Mapping(target = "data", expression = "java(toDto(paginated.getData()))")
  @Mapping(target = "currentPage", expression = "java(paginated.getCurrentPage())")
  @Mapping(target = "totalItems", expression = "java(paginated.getTotalItems())")
  @Mapping(target = "totalPages", expression = "java(paginated.getTotalPages())")
  Paginated<CategoryResponseDto> toDtoPaginated(Paginated<Category> paginated);
}
