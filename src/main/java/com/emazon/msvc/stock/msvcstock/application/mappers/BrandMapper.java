package com.emazon.msvc.stock.msvcstock.application.mappers;

import com.emazon.msvc.stock.msvcstock.application.dtos.brand.BrandResponseDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.brand.CreateBrandRequestDto;
import com.emazon.msvc.stock.msvcstock.domain.models.Brand;
import com.emazon.msvc.stock.msvcstock.domain.models.Paginated;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BrandMapper {
  BrandResponseDto toDto(Brand brand);
  List<BrandResponseDto> toDto(List<Brand> brands);
  Brand toDomain(CreateBrandRequestDto brandDto);
  @Mapping(target = "data", expression = "java(toDto(paginated.getData()))")
  @Mapping(target = "currentPage", expression = "java(paginated.getCurrentPage())")
  @Mapping(target = "totalItems", expression = "java(paginated.getTotalItems())")
  @Mapping(target = "totalPages", expression = "java(paginated.getTotalPages())")
  Paginated<BrandResponseDto> toDtoPaginated(Paginated<Brand> paginated);
}
