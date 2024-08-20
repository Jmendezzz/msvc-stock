package com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.repositories.jpa;

import com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.entities.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaBrandRepository extends JpaRepository<BrandEntity, Long> {
  Optional<BrandEntity> findByName(String brandName);
}
