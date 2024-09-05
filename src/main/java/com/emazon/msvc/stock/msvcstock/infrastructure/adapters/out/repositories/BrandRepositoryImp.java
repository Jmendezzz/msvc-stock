package com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.repositories;

import com.emazon.msvc.stock.msvcstock.domain.models.Brand;
import com.emazon.msvc.stock.msvcstock.domain.models.Paginated;
import com.emazon.msvc.stock.msvcstock.domain.models.Pagination;
import com.emazon.msvc.stock.msvcstock.domain.models.Sorting;
import com.emazon.msvc.stock.msvcstock.domain.ports.out.repositories.BrandRepository;
import com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.entities.BrandEntity;
import com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.mappers.DboBrandMapper;
import com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.repositories.jpa.JpaBrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class BrandRepositoryImp implements BrandRepository {
  private final JpaBrandRepository jpaRepository;
  private final DboBrandMapper mapper;
  @Override
  public Brand save(Brand brand) {
    BrandEntity brandEntity = mapper.toEntity(brand);
    return mapper.toDomain(jpaRepository.save(brandEntity));
  }

  @Override
  public Optional<Brand> findById(Long id) {
    return jpaRepository.findById(id).map(mapper::toDomain);
  }

  @Override
  public Optional<Brand> findByName(String brandName) {
    return jpaRepository.findByName(brandName).map(mapper::toDomain);
  }

  @Override
  public Paginated<Brand> findAll(Pagination pagination, Sorting sort) {
    Pageable pageable = PageRequest.of(
            pagination.getPage(),
            pagination.getSize(),
            Sort.by(Sort.Direction.fromString(sort.getDirection().name()), sort.getSortBy())
    );

    Page<BrandEntity> page = jpaRepository.findAll(pageable);
    return mapper.toDomainPaginated(page);
  }

}
