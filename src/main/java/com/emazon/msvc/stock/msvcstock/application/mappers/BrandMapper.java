package com.emazon.msvc.stock.msvcstock.application.mappers;

import com.emazon.msvc.stock.msvcstock.application.dtos.brand.BrandDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.brand.CreateBrandDto;
import com.emazon.msvc.stock.msvcstock.domain.models.Brand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BrandMapper {
  BrandDto toDto(Brand brand);
  Brand toDomain(CreateBrandDto brandDto);
}
