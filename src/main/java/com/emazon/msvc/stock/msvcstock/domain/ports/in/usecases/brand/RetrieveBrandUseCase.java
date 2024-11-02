package com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.brand;

import com.emazon.msvc.stock.msvcstock.domain.models.Brand;
import com.emazon.msvc.stock.msvcstock.domain.models.Paginated;
import com.emazon.msvc.stock.msvcstock.domain.models.Pagination;
import com.emazon.msvc.stock.msvcstock.domain.models.Sorting;

import java.util.List;
import java.util.Optional;

public interface RetrieveBrandUseCase {
  Paginated<Brand> retrieveBrands(Pagination pagination, Sorting sort);
  List<Brand> retrieveAllBrands();
  Optional<Brand> retrieveBrandById(Long id);
}
