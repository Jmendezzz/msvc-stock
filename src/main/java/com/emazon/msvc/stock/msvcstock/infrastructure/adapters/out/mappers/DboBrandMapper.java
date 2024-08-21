package com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.mappers;

import com.emazon.msvc.stock.msvcstock.domain.models.Brand;
import com.emazon.msvc.stock.msvcstock.domain.models.Paginated;
import com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.entities.BrandEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface DboBrandMapper {
  Brand toDomain(BrandEntity brandEntity);
  BrandEntity toEntity(Brand brand);

  @Mapping(target = "data", expression = "java(toDomain((page.getContent())))")
  @Mapping(target = "currentPage", expression = "java((long) page.getNumber())")
  @Mapping(target = "totalItems", expression = "java(page.getTotalElements())")
  @Mapping(target = "totalPages", expression = "java((long) page.getTotalPages())")
  Paginated<Brand> toDomainPaginated(Page<BrandEntity> brandEntityPagination);
}
