package com.emazon.msvc.stock.msvcstock.domain.usecases.brand;

import com.emazon.msvc.stock.msvcstock.domain.exceptions.brand.DuplicateBrandNameException;
import com.emazon.msvc.stock.msvcstock.domain.models.Brand;
import com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.CreateBrandUseCase;
import com.emazon.msvc.stock.msvcstock.domain.ports.out.repositories.BrandRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateBrandUseCaseImp implements CreateBrandUseCase {
  private final BrandRepository brandRepository;
  @Override
  public Brand create(Brand brand) {
    if(brandRepository.findByName(brand.getName()).isPresent()) throw new DuplicateBrandNameException();

    return brandRepository.save(brand);
  }
}
