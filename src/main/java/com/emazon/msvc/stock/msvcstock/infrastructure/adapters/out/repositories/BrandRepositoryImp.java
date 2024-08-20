package com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.repositories;

import com.emazon.msvc.stock.msvcstock.domain.models.Brand;
import com.emazon.msvc.stock.msvcstock.domain.ports.out.repositories.BrandRepository;
import com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.entities.BrandEntity;
import com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.mappers.DboBrandMapper;
import com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.repositories.jpa.JpaBrandRepository;
import lombok.AllArgsConstructor;
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
  public Optional<Brand> findByName(String brandName) {
    return jpaRepository.findByName(brandName).map(mapper::toDomain);
  }
}
