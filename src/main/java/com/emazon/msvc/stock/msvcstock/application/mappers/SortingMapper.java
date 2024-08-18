package com.emazon.msvc.stock.msvcstock.application.mappers;

import com.emazon.msvc.stock.msvcstock.application.dtos.sorting.SortingDto;
import com.emazon.msvc.stock.msvcstock.domain.models.Sorting;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SortingMapper {

  @Mapping(source = "sortBy", target = "field")
  @Mapping(source = "direction", target = "direction")
  Sorting toDomain(SortingDto sortingDto);
}
