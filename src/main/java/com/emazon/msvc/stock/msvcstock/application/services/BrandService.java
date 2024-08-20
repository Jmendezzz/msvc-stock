package com.emazon.msvc.stock.msvcstock.application.services;

import com.emazon.msvc.stock.msvcstock.application.dtos.brand.BrandDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.brand.CreateBrandDto;

public interface BrandService {
  BrandDto createBrand(CreateBrandDto createBrandDto);
}
