package com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.repositories.jpa;

import com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
