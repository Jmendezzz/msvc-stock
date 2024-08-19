package com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.repositories;

import com.emazon.msvc.stock.msvcstock.domain.models.Category;
import com.emazon.msvc.stock.msvcstock.domain.models.Paginated;
import com.emazon.msvc.stock.msvcstock.domain.models.Pagination;
import com.emazon.msvc.stock.msvcstock.domain.models.Sorting;
import com.emazon.msvc.stock.msvcstock.domain.ports.out.repositories.CategoryRepository;
import com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.entities.CategoryEntity;
import com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.mappers.DboCategoryMapper;
import com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.repositories.jpa.JpaCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

  @Override
  public Paginated<Category> findAll(Pagination pagination, Sorting sort) {
    Pageable pageable = PageRequest.of(
            pagination.getPage(),
            pagination.getSize(),
            Sort.by(                Sort.Direction.fromString(sort.getDirection().name()) // Correctly convert direction string to enum
                    ,sort.getField())
    );

    Page<CategoryEntity> page = jpaRepository.findAll(pageable);

    return mapper.toDomainPaginated(page);
  }

}
