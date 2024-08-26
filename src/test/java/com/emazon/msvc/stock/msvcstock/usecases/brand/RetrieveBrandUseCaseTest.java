package com.emazon.msvc.stock.msvcstock.usecases.brand;

import com.emazon.msvc.stock.msvcstock.domain.models.Brand;
import com.emazon.msvc.stock.msvcstock.domain.models.Paginated;
import com.emazon.msvc.stock.msvcstock.domain.models.Pagination;
import com.emazon.msvc.stock.msvcstock.domain.models.Sorting;
import com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.brand.RetrieveBrandUseCase;
import com.emazon.msvc.stock.msvcstock.domain.ports.out.repositories.BrandRepository;
import com.emazon.msvc.stock.msvcstock.domain.usecases.brand.RetrieveBrandUseCaseImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RetrieveBrandUseCaseTest {

  private RetrieveBrandUseCase retrieveBrandUseCase;

  @Mock
  private BrandRepository brandRepository;

  @BeforeEach
  void setUp() {
    retrieveBrandUseCase = new RetrieveBrandUseCaseImp(brandRepository);
  }

  @Test
  void getPaginatedBrandsWithNoResultsTest() {
    Pagination expectedPagination = new Pagination(1, 10);
    Sorting expectedSorting = new Sorting("name", "ASC");


    Paginated<Brand> expectedResult = new Paginated<Brand>(Collections.emptyList(), 0L,0L,0L);

    when(retrieveBrandUseCase.retrieveBrands(expectedPagination, expectedSorting)).thenReturn(expectedResult);

    Paginated<Brand> actualResult = retrieveBrandUseCase.retrieveBrands(expectedPagination, expectedSorting);

    assertEquals(expectedResult, actualResult);

  }

  @Test
  void getPaginatedBrandsWithResultsTest() {
    Pagination expectedPagination = new Pagination(0, 10);
    Sorting expectedSorting = new Sorting("name", "ASC");

    Brand brand = new Brand(1L, "brandName", "brandDescription");
    Paginated<Brand> expectedResult = new Paginated<Brand>(Collections.singletonList(brand), 0L,1L,1L);

    when(retrieveBrandUseCase.retrieveBrands(expectedPagination, expectedSorting)).thenReturn(expectedResult);

    Paginated<Brand> actualResult = retrieveBrandUseCase.retrieveBrands(expectedPagination, expectedSorting);

    assertEquals(expectedResult, actualResult);
  }

  @Test
  void getBrandByIdTest() {
    Brand brand = new Brand(1L, "brandName", "brandDescription");
    when(retrieveBrandUseCase.retrieveBrandById(1L)).thenReturn(Optional.of(brand));

    Optional<Brand> actualBrand = retrieveBrandUseCase.retrieveBrandById(1L);

    assertEquals(brand, actualBrand.get());
  }

}
