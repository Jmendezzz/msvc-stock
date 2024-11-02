package com.emazon.msvc.stock.msvcstock.domain.ports.out.repositories;

import com.emazon.msvc.stock.msvcstock.domain.models.Brand;
import com.emazon.msvc.stock.msvcstock.domain.models.Paginated;
import com.emazon.msvc.stock.msvcstock.domain.models.Pagination;
import com.emazon.msvc.stock.msvcstock.domain.models.Sorting;

import java.util.List;
import java.util.Optional;

public interface BrandRepository {
  Brand save(Brand brand);
  Optional<Brand> findByName(String brandName);
  Paginated<Brand> findAll(Pagination pagination, Sorting sort);
  Optional<Brand> findById(Long id);
  List<Brand> findAll();
}
