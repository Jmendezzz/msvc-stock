package com.emazon.msvc.stock.msvcstock.application.handlers;

import com.emazon.msvc.stock.msvcstock.application.dtos.brand.BrandResponseDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.brand.CreateBrandRequestDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.pagination.PaginationDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.sorting.SortingDto;
import com.emazon.msvc.stock.msvcstock.application.mappers.BrandMapper;
import com.emazon.msvc.stock.msvcstock.application.mappers.PaginationMapper;
import com.emazon.msvc.stock.msvcstock.application.mappers.SortingMapper;
import com.emazon.msvc.stock.msvcstock.application.handlers.imp.BrandHandlerImp;
import com.emazon.msvc.stock.msvcstock.domain.models.Brand;
import com.emazon.msvc.stock.msvcstock.domain.models.Paginated;
import com.emazon.msvc.stock.msvcstock.domain.models.Pagination;
import com.emazon.msvc.stock.msvcstock.domain.models.Sorting;
import com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.brand.CreateBrandUseCase;
import com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.brand.RetrieveBrandUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BrandHandlerTest {
  private BrandHandler brandHandler;
  @Mock
  private CreateBrandUseCase createBrandUseCase;
  @Mock
  private RetrieveBrandUseCase retrieveBrandsUseCase;
  @Mock
  private BrandMapper mapper;
  @Mock
  private PaginationMapper paginationMapper;
  @Mock
  private SortingMapper sortingMapper;


  @BeforeEach
  public void setUp() {
    brandHandler = new BrandHandlerImp(
      createBrandUseCase,
      retrieveBrandsUseCase,
      mapper,
      paginationMapper,
      sortingMapper
    );
  }

  @Test
  void createBrandTest() {
    CreateBrandRequestDto createBrandRequestDto = new CreateBrandRequestDto("brandName", "brandDescription");
    Brand createBrandDtoMapped = new Brand(null, "brandName", "brandDescription");

    Brand createdBrand = new Brand(1L, "brandName", "brandDescription");
    BrandResponseDto expectedResult = new BrandResponseDto(1L, "brandName", "brandDescription");

    when(mapper.toDomain(createBrandRequestDto)).thenReturn(createBrandDtoMapped);
    when(createBrandUseCase.create(createBrandDtoMapped)).thenReturn(createdBrand);
    when(mapper.toDto(createdBrand)).thenReturn(expectedResult);

    BrandResponseDto brandCreated = brandHandler.createBrand(createBrandRequestDto);

    assertEquals(expectedResult.id(), brandCreated.id());
    assertEquals(expectedResult.name(), brandCreated.name());
    assertEquals(expectedResult.description(), brandCreated.description());
  }

  @Test
  void retrieveBrandsTest(){
    PaginationDto paginationDto = new PaginationDto(0, 10);
    SortingDto sortingDto = new SortingDto("name", "ASC");

    // Prepare input mapped to domain
    Pagination pagination = new Pagination(0, 10);
    Sorting sorting = new Sorting("name", "ASC");


    // Use case expected result
    Paginated<Brand> expectedResult = new Paginated<Brand>(List.of(
      new Brand(1L, "First", "brandDescription1"),
      new Brand(2L, "Second", "brandDescription2")
    ), 0L,0L,0L);

    // Prepare expected result
    Paginated<BrandResponseDto> expectedPaginatedBrandDto = new Paginated<BrandResponseDto>(List.of(
      new BrandResponseDto(1L, "First", "brandDescription1"),
      new BrandResponseDto(2L, "Second", "brandDescription2")
    ), 0L,0L,0L);

    when(paginationMapper.toDomain(paginationDto)).thenReturn(pagination);
    when(sortingMapper.toDomain(sortingDto)).thenReturn(sorting);
    when(retrieveBrandsUseCase.retrieveBrands(pagination, sorting)).thenReturn(expectedResult);
    when(mapper.toDtoPaginated(expectedResult)).thenReturn(expectedPaginatedBrandDto);

    Paginated<BrandResponseDto> result = brandHandler.retrieveBrands(paginationDto, sortingDto);

    assertEquals(expectedPaginatedBrandDto, result);
  }

}
