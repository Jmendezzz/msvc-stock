package com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.mappers;

import com.emazon.msvc.stock.msvcstock.domain.models.Category;
import com.emazon.msvc.stock.msvcstock.domain.models.Paginated;
import com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.entities.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface DboCategoryMapper {
  Category toDomain(CategoryEntity categoryEntity);
  List<Category> toDomain(List<CategoryEntity> categoryEntities);
  Set<Category> toDomainSet(List<CategoryEntity> categoryEntities);
  CategoryEntity toEntity(Category category);

  @Mapping(target = "data", expression = "java(toDomain((page.getContent())))")
  @Mapping(target = "currentPage", expression = "java((long) page.getNumber())")
  @Mapping(target = "totalItems", expression = "java(page.getTotalElements())")
  @Mapping(target = "totalPages", expression = "java((long) page.getTotalPages())")
  Paginated<Category> toDomainPaginated(Page<CategoryEntity> page);

}
