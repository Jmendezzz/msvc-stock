package com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases;

import com.emazon.msvc.stock.msvcstock.domain.models.Brand;
import com.emazon.msvc.stock.msvcstock.domain.models.Paginated;
import com.emazon.msvc.stock.msvcstock.domain.models.Pagination;
import com.emazon.msvc.stock.msvcstock.domain.models.Sorting;

public interface RetrieveBrandUseCase {
  Paginated<Brand> retrieveBrands(Pagination pagination, Sorting sort);
}
