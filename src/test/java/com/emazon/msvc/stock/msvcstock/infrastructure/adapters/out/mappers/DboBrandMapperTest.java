package com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.mappers;

import com.emazon.msvc.stock.msvcstock.domain.models.Brand;
import com.emazon.msvc.stock.msvcstock.domain.models.Paginated;
import com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.entities.BrandEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class DboBrandMapperTest {
  private DboBrandMapper dboBrandMapper;

  @BeforeEach
  public void setUp() {
    dboBrandMapper = Mappers.getMapper(DboBrandMapper.class);
  }

  @Test

  void testMapBrandToDboBrand() {
    Brand brand = new Brand(1L, "brandName", "brandDescription");
    BrandEntity dboBrand = dboBrandMapper.toEntity(brand);

    assertEquals(brand.getId(), dboBrand.getId());
    assertEquals(brand.getName(), dboBrand.getName());
    assertEquals(brand.getDescription(), dboBrand.getDescription());
  }

  @Test
  void testMapDboBrandToBrand() {
    BrandEntity dboBrand = new BrandEntity(1L, "brandName", "brandDescription");
    Brand brand = dboBrandMapper.toDomain(dboBrand);

    assertEquals(dboBrand.getId(), brand.getId());
    assertEquals(dboBrand.getName(), brand.getName());
    assertEquals(dboBrand.getDescription(), brand.getDescription());
  }

  @Test
  void testMapDboBrandListToBrand(){
    List<BrandEntity> dboBrands = List.of(new BrandEntity(1L, "brandName", "brandDescription"));
    List<Brand> brands = dboBrandMapper.toDomain(dboBrands);

    assertEquals(dboBrands.get(0).getId(), brands.get(0).getId());
    assertEquals(dboBrands.get(0).getName(), brands.get(0).getName());
    assertEquals(dboBrands.get(0).getDescription(), brands.get(0).getDescription());
    assertEquals(dboBrands.size(), brands.size());
  }

  @Test
  void toDomainPaginated(){
    BrandEntity dboBrand = new BrandEntity(1L, "brandName", "brandDescription");
    List<BrandEntity> dboBrands = List.of(dboBrand);
    Paginated<Brand> paginated = dboBrandMapper.toDomainPaginated(new PageImpl<>(dboBrands));

    assertEquals(dboBrands.get(0).getId(), paginated.getData().get(0).getId());
    assertEquals(dboBrands.get(0).getName(), paginated.getData().get(0).getName());
    assertEquals(dboBrands.get(0).getDescription(), paginated.getData().get(0).getDescription());
    assertEquals(dboBrands.size(), paginated.getData().size());
  }



}
