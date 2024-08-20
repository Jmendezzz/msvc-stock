package com.emazon.msvc.stock.msvcstock.usecases.category;

import com.emazon.msvc.stock.msvcstock.domain.enums.SortDirection;
import com.emazon.msvc.stock.msvcstock.domain.exceptions.sorting.InvalidSortByFieldException;
import com.emazon.msvc.stock.msvcstock.domain.models.Category;
import com.emazon.msvc.stock.msvcstock.domain.models.Paginated;
import com.emazon.msvc.stock.msvcstock.domain.models.Pagination;
import com.emazon.msvc.stock.msvcstock.domain.models.Sorting;
import com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.RetrieveCategoryUseCase;
import com.emazon.msvc.stock.msvcstock.domain.ports.out.repositories.CategoryRepository;
import com.emazon.msvc.stock.msvcstock.domain.usecases.category.RetrieveCategoryUseCaseImp;
import com.emazon.msvc.stock.msvcstock.domain.validations.imp.CategorySortingValidation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RetrieveCategoryUseCaseTest {
  private RetrieveCategoryUseCase retrieveCategoryUseCase;

  @Mock
  private CategoryRepository categoryRepository;

  @Mock
  private CategorySortingValidation categorySortingValidation;

  @BeforeEach
  public void setUp() {
    retrieveCategoryUseCase = new RetrieveCategoryUseCaseImp(categoryRepository,categorySortingValidation );
  }

  @Test
  void getPaginatedCategoriesWithNoResultsTest() {
    Pagination expectedPagination = new Pagination(1, 10);
    Sorting expectedSorting = new Sorting("name", SortDirection.ASC);

    Paginated<Category> expectedResult = new Paginated<Category>(Collections.emptyList(), 0L,0L,0L);

    when(retrieveCategoryUseCase.retrieveCategories(expectedPagination, expectedSorting)).thenReturn(expectedResult);

    Paginated<Category> actualResult = retrieveCategoryUseCase.retrieveCategories(expectedPagination, expectedSorting);

    assertEquals(expectedResult, actualResult);
  }

  @Test
  void getPaginatedCategoriesWithResultsTest() {
    Pagination expectedPagination = new Pagination(0, 10);
    Sorting expectedSorting = new Sorting("name", SortDirection.ASC);

    Category category = new Category(1L, "categoryName", "categoryDescription", LocalDateTime.now());
    Paginated<Category> expectedResult = new Paginated<Category>(Collections.singletonList(category), 0L,1L,1L);

    when(retrieveCategoryUseCase.retrieveCategories(expectedPagination, expectedSorting)).thenReturn(expectedResult);

    Paginated<Category> actualResult = retrieveCategoryUseCase.retrieveCategories(expectedPagination, expectedSorting);

    assertEquals(expectedResult, actualResult);
  }

  @Test
  void invalidSortingByFieldTest() {
    Pagination pagination = new Pagination(0, 10);
    Sorting sorting = new Sorting("invalidField", SortDirection.ASC);

    doThrow(InvalidSortByFieldException.class).when(categorySortingValidation).isValidSortBy(sorting.getField());

    assertThrows(InvalidSortByFieldException.class, () -> retrieveCategoryUseCase.retrieveCategories(pagination, sorting));
  }

}
