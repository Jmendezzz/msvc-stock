package com.emazon.msvc.stock.msvcstock.application.mappers;

import com.emazon.msvc.stock.msvcstock.application.dtos.pagination.PaginationDto;
import com.emazon.msvc.stock.msvcstock.domain.models.Pagination;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaginationMapper {
  Pagination toDomain(PaginationDto paginationDto);
}
