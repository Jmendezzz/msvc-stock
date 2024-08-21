package com.emazon.msvc.stock.msvcstock.domain.usecases.brand;

import com.emazon.msvc.stock.msvcstock.domain.models.Brand;
import com.emazon.msvc.stock.msvcstock.domain.models.Paginated;
import com.emazon.msvc.stock.msvcstock.domain.models.Pagination;
import com.emazon.msvc.stock.msvcstock.domain.models.Sorting;
import com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.RetrieveBrandUseCase;
import com.emazon.msvc.stock.msvcstock.domain.ports.out.repositories.BrandRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RetrieveBrandUseCaseImp implements RetrieveBrandUseCase {
  private final BrandRepository repository;

  @Override
  public Paginated<Brand> retrieveBrands(Pagination pagination, Sorting sort) {
    return null;
  }
}
