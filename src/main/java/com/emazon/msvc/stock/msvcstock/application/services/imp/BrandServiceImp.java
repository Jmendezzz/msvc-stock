package com.emazon.msvc.stock.msvcstock.application.services.imp;

import com.emazon.msvc.stock.msvcstock.application.dtos.brand.BrandDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.brand.CreateBrandDto;
import com.emazon.msvc.stock.msvcstock.application.mappers.BrandMapper;
import com.emazon.msvc.stock.msvcstock.application.services.BrandService;
import com.emazon.msvc.stock.msvcstock.domain.models.Brand;
import com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.CreateBrandUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BrandServiceImp implements BrandService {
  private final CreateBrandUseCase createBrandUseCase;
  private final BrandMapper mapper;
  @Override
  public BrandDto createBrand(CreateBrandDto createBrandDto) {
    Brand brandCreated = createBrandUseCase.create(mapper.toDomain(createBrandDto));

    return mapper.toDto(brandCreated);
  }
}
