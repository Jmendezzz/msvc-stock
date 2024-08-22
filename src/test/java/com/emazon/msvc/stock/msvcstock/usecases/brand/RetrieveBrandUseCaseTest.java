package com.emazon.msvc.stock.msvcstock.usecases.brand;

import com.emazon.msvc.stock.msvcstock.domain.models.Brand;
import com.emazon.msvc.stock.msvcstock.domain.models.Paginated;
import com.emazon.msvc.stock.msvcstock.domain.models.Pagination;
import com.emazon.msvc.stock.msvcstock.domain.models.Sorting;
import com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.RetrieveBrandUseCase;
import com.emazon.msvc.stock.msvcstock.domain.ports.out.repositories.BrandRepository;
import com.emazon.msvc.stock.msvcstock.domain.usecases.brand.RetrieveBrandUseCaseImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RetrieveBrandUseCaseTest {

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

  }



}
