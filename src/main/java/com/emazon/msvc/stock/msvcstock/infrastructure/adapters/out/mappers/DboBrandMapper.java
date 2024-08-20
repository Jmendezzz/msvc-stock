package com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.mappers;

import com.emazon.msvc.stock.msvcstock.domain.models.Brand;
import com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.entities.BrandEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DboBrandMapper {
  Brand toDomain(BrandEntity brandEntity);
  BrandEntity toEntity(Brand brand);
}
