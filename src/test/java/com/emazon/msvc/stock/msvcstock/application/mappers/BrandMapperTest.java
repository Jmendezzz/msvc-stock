package com.emazon.msvc.stock.msvcstock.application.mappers;

import com.emazon.msvc.stock.msvcstock.application.dtos.brand.BrandResponseDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.brand.CreateBrandRequestDto;
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
    BrandResponseDto expectedBrandResponseDto = new BrandResponseDto(1L, "brandName", "brandDescription");
    Brand brand = new Brand(1L, "brandName", "brandDescription");
    BrandResponseDto brandResponseDto = brandMapper.toDto(brand);

    assertEquals(expectedBrandResponseDto.id(), brandResponseDto.id());
    assertEquals(expectedBrandResponseDto.name(), brandResponseDto.name());
    assertEquals(expectedBrandResponseDto.description(), brandResponseDto.description());
  }
  @Test
  void testMapNullBrandToBrandDto() {
    Brand brand = null;
    BrandResponseDto brandResponseDto = brandMapper.toDto(brand);
    assertNull(brandResponseDto);
  }

  @Test
  void testCreateBrandDtoToBrand() {
    Brand expectedBrand = new Brand(null, "brandName", "brandDescription");
    CreateBrandRequestDto createBrandRequestDto = new CreateBrandRequestDto("brandName", "brandDescription");
    Brand brand = brandMapper.toDomain(createBrandRequestDto);

    assertEquals(expectedBrand.getId(), brand.getId());
    assertEquals(expectedBrand.getName(), brand.getName());
    assertEquals(expectedBrand.getDescription(), brand.getDescription());
  }
  @Test
  void testCreateNullBrandDtoToBrand() {
    CreateBrandRequestDto createBrandRequestDto = null;
    Brand brand = brandMapper.toDomain(createBrandRequestDto);
    assertNull(brand);
  }

  @Test
  void testListBrandToBrandDto() {
    BrandResponseDto expectedBrandResponseDto = new BrandResponseDto(1L, "brandName", "brandDescription");
    Brand brand = new Brand(1L, "brandName", "brandDescription");
    List<Brand> brands = new ArrayList<>();
    brands.add(brand);
    List<BrandResponseDto> brandResponseDtos = brandMapper.toDto(brands);

    assertEquals(expectedBrandResponseDto.id(), brandResponseDtos.get(0).id());
    assertEquals(expectedBrandResponseDto.name(), brandResponseDtos.get(0).name());
    assertEquals(expectedBrandResponseDto.description(), brandResponseDtos.get(0).description());
  }
  @Test
  void testListNullBrandToBrandDto() {
    List<Brand> brands = null;
    List<BrandResponseDto> brandResponseDtos = brandMapper.toDto(brands);
    assertNull(brandResponseDtos);
  }

  @Test
  void testPaginatedBrandToPaginatedBrandDto() {
    BrandResponseDto expectedBrandResponseDto = new BrandResponseDto(1L, "brandName", "brandDescription");
    Brand brand = new Brand(1L, "brandName", "brandDescription");
    List<Brand> brands = new ArrayList<>();
    brands.add(brand);
    Paginated<Brand> paginatedBrands = new Paginated<>(brands, 0L, 1L, 1L);
    Paginated<BrandResponseDto> paginatedBrandDtos = brandMapper.toDtoPaginated(paginatedBrands);

    assertEquals(expectedBrandResponseDto.id(), paginatedBrandDtos.getData().get(0).id());
    assertEquals(expectedBrandResponseDto.name(), paginatedBrandDtos.getData().get(0).name());
    assertEquals(expectedBrandResponseDto.description(), paginatedBrandDtos.getData().get(0).description());
  }

  @Test
  void testPaginatedNullBrandToPaginatedBrandDto() {
    Paginated<Brand> paginatedBrands = null;
    Paginated<BrandResponseDto> paginatedBrandDtos = brandMapper.toDtoPaginated(paginatedBrands);
    assertNull(paginatedBrandDtos);
  }
}
