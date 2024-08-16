package com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.repositories;

import com.emazon.msvc.stock.msvcstock.domain.models.Category;
import com.emazon.msvc.stock.msvcstock.domain.ports.out.repositories.CategoryRepository;
import com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.entities.CategoryEntity;
import com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.mappers.DboCategoryMapper;
import com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.repositories.jpa.JpaCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class CategoryRepositoryImp implements CategoryRepository {
  private final JpaCategoryRepository jpaRepository;
  private final DboCategoryMapper mapper;

  @Override
  public Category save(Category category) {
    CategoryEntity categoryEntity = mapper.toEntity(category);
    return mapper.toDomain(jpaRepository.save(categoryEntity));
  }

  @Override
  public Optional<Category> findByName(String name) {
    return jpaRepository.findByName(name).map(mapper::toDomain);
  }
}
