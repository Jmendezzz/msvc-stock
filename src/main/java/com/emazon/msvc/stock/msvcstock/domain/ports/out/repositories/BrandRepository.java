package com.emazon.msvc.stock.msvcstock.domain.ports.out.repositories;

import com.emazon.msvc.stock.msvcstock.domain.models.Brand;

import java.util.Optional;

public interface BrandRepository {
  Brand save(Brand brand);
  Optional<Brand> findByName(String brandName);
}
