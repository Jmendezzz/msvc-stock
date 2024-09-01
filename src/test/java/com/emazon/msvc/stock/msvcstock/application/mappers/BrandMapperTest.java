package com.emazon.msvc.stock.msvcstock.application.mappers;

import com.emazon.msvc.stock.msvcstock.application.dtos.brand.BrandDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.brand.CreateBrandDto;
import com.emazon.msvc.stock.msvcstock.domain.models.Brand;
import com.emazon.msvc.stock.msvcstock.domain.models.Paginated;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class BrandMapperTest {
  private BrandMapper brandMapper;

  @BeforeEach
  void setUp() {
    brandMapper = Mappers.getMapper(BrandMapper.class);
  }

  @Test
  void testMapBrandToBrandDto() {
    BrandDto expectedBrandDto = new BrandDto(1L, "brandName", "brandDescription");
    Brand brand = new Brand(1L, "brandName", "brandDescription");
    BrandDto brandDto = brandMapper.toDto(brand);

    assertEquals(expectedBrandDto.id(), brandDto.id());
    assertEquals(expectedBrandDto.name(), brandDto.name());
    assertEquals(expectedBrandDto.description(), brandDto.description());
  }
  @Test
  void testMapNullBrandToBrandDto() {
    Brand brand = null;
    BrandDto brandDto = brandMapper.toDto(brand);
    assertNull(brandDto);
  }

  @Test
  void testCreateBrandDtoToBrand() {
    Brand expectedBrand = new Brand(null, "brandName", "brandDescription");
    CreateBrandDto createBrandDto = new CreateBrandDto("brandName", "brandDescription");
    Brand brand = brandMapper.toDomain(createBrandDto);

    assertEquals(expectedBrand.getId(), brand.getId());
    assertEquals(expectedBrand.getName(), brand.getName());
    assertEquals(expectedBrand.getDescription(), brand.getDescription());
  }
  @Test
  void testCreateNullBrandDtoToBrand() {
    CreateBrandDto createBrandDto = null;
    Brand brand = brandMapper.toDomain(createBrandDto);
    assertNull(brand);
  }

  @Test
  void testListBrandToBrandDto() {
    BrandDto expectedBrandDto = new BrandDto(1L, "brandName", "brandDescription");
    Brand brand = new Brand(1L, "brandName", "brandDescription");
    List<Brand> brands = new ArrayList<>();
    brands.add(brand);
    List<BrandDto> brandDtos = brandMapper.toDto(brands);

    assertEquals(expectedBrandDto.id(), brandDtos.get(0).id());
    assertEquals(expectedBrandDto.name(), brandDtos.get(0).name());
    assertEquals(expectedBrandDto.description(), brandDtos.get(0).description());
  }
  @Test
  void testListNullBrandToBrandDto() {
    List<Brand> brands = null;
    List<BrandDto> brandDtos = brandMapper.toDto(brands);
    assertNull(brandDtos);
  }

  @Test
  void testPaginatedBrandToPaginatedBrandDto() {
    BrandDto expectedBrandDto = new BrandDto(1L, "brandName", "brandDescription");
    Brand brand = new Brand(1L, "brandName", "brandDescription");
    List<Brand> brands = new ArrayList<>();
    brands.add(brand);
    Paginated<Brand> paginatedBrands = new Paginated<>(brands, 0L, 1L, 1L);
    Paginated<BrandDto> paginatedBrandDtos = brandMapper.toDtoPaginated(paginatedBrands);

    assertEquals(expectedBrandDto.id(), paginatedBrandDtos.getData().get(0).id());
    assertEquals(expectedBrandDto.name(), paginatedBrandDtos.getData().get(0).name());
    assertEquals(expectedBrandDto.description(), paginatedBrandDtos.getData().get(0).description());
  }

  @Test
  void testPaginatedNullBrandToPaginatedBrandDto() {
    Paginated<Brand> paginatedBrands = null;
    Paginated<BrandDto> paginatedBrandDtos = brandMapper.toDtoPaginated(paginatedBrands);
    assertNull(paginatedBrandDtos);
  }
}
