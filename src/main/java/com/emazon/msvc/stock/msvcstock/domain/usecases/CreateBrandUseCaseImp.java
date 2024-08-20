package com.emazon.msvc.stock.msvcstock.domain.usecases;

import com.emazon.msvc.stock.msvcstock.domain.models.Brand;
import com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.CreateBrandUseCase;
import com.emazon.msvc.stock.msvcstock.domain.ports.out.repositories.BrandRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateBrandUseCaseImp implements CreateBrandUseCase {
  private final BrandRepository brandRepository;
  @Override
  public Brand create(Brand brand) {
    return brandRepository.save(brand);
  }
}
