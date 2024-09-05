package com.emazon.msvc.stock.msvcstock.domain.usecases.category;

import com.emazon.msvc.stock.msvcstock.domain.exceptions.sorting.InvalidSortByFieldException;
import com.emazon.msvc.stock.msvcstock.domain.models.Category;
import com.emazon.msvc.stock.msvcstock.domain.models.Paginated;
import com.emazon.msvc.stock.msvcstock.domain.models.Pagination;
import com.emazon.msvc.stock.msvcstock.domain.models.Sorting;
import com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.category.RetrieveCategoryUseCase;
import com.emazon.msvc.stock.msvcstock.domain.ports.out.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RetrieveCategoryUseCaseTest {
  private RetrieveCategoryUseCase retrieveCategoryUseCase;

  @Mock
  private CategoryRepository categoryRepository;

  @BeforeEach
  public void setUp() {
    retrieveCategoryUseCase = new RetrieveCategoryUseCaseImp(categoryRepository);
  }

  @Test
  void getPaginatedCategoriesWithNoResultsTest() {
    Pagination expectedPagination = new Pagination(1, 10);
    Sorting expectedSorting = new Sorting("name", "ASC");

    Paginated<Category> expectedResult = new Paginated<Category>(Collections.emptyList(), 0L,0L,0L);

    when(categoryRepository.findAll(any(Pagination.class), any(Sorting.class))).thenReturn(expectedResult);

    Paginated<Category> actualResult = retrieveCategoryUseCase.retrieveCategories(expectedPagination, expectedSorting);

    assertEquals(expectedResult, actualResult);
  }

  @Test
  void getPaginatedCategoriesWithResultsTest() {
    Pagination expectedPagination = new Pagination(0, 10);
    Sorting expectedSorting = new Sorting("name", "ASC");

    Category category = new Category(1L, "categoryName", "categoryDescription");
    Paginated<Category> expectedResult = new Paginated<Category>(Collections.singletonList(category), 0L,1L,1L);

    when(categoryRepository.findAll(any(Pagination.class), any(Sorting.class))).thenReturn(expectedResult);

    Paginated<Category> actualResult = retrieveCategoryUseCase.retrieveCategories(expectedPagination, expectedSorting);

    assertEquals(expectedResult, actualResult);
  }

  @Test
  void invalidSortingByFieldTest() {
    Pagination pagination = new Pagination(0, 10);
    Sorting sorting = new Sorting("invalidField", "ASC");

    assertThrows(InvalidSortByFieldException.class, () -> retrieveCategoryUseCase.retrieveCategories(pagination, sorting));
  }

  @Test
  void getCategoriesByIdsTest() {
    Category category = new Category(1L, "categoryName", "categoryDescription");
    Set<Category> expectedResult = Collections.singleton(category);
    Set<Long> ids = Set.of(1L);
    when(retrieveCategoryUseCase.retrieveCategoriesByIds(ids)).thenReturn(expectedResult);

    Set<Category> actualResult = retrieveCategoryUseCase.retrieveCategoriesByIds(ids);

    assertEquals(expectedResult, actualResult);
  }

}
