package com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.repositories.jpa;

import com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.entities.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaCategoryRepository extends JpaRepository<CategoryEntity, Long> {
  Optional<CategoryEntity> findByName(String name);
  Page<CategoryEntity> findAll(Pageable pageable);
}
