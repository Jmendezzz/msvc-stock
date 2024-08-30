package com.emazon.msvc.stock.msvcstock.application.services;

import com.emazon.msvc.stock.msvcstock.application.dtos.category.CategoryDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.category.CreateCategoryDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.pagination.PaginationDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.sorting.SortingDto;
import com.emazon.msvc.stock.msvcstock.application.mappers.CategoryMapper;
import com.emazon.msvc.stock.msvcstock.application.mappers.PaginationMapper;
import com.emazon.msvc.stock.msvcstock.application.mappers.SortingMapper;
import com.emazon.msvc.stock.msvcstock.application.services.CategoryService;
import com.emazon.msvc.stock.msvcstock.application.services.imp.CategoryServiceImp;
import com.emazon.msvc.stock.msvcstock.domain.models.Category;
import com.emazon.msvc.stock.msvcstock.domain.models.Paginated;
import com.emazon.msvc.stock.msvcstock.domain.models.Pagination;
import com.emazon.msvc.stock.msvcstock.domain.models.Sorting;
import com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.category.CreateCategoryUseCase;
import com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.category.RetrieveCategoryUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

  private CategoryService categoryService;
  @Mock
  private CreateCategoryUseCase createCategoryUseCase;
  @Mock
  private RetrieveCategoryUseCase retrieveCategoriesUseCase;
  @Mock
  private CategoryMapper mapper;
  @Mock
  private PaginationMapper paginationMapper;
  @Mock
  private SortingMapper sortingMapper;

  @BeforeEach
  public void setUp() {
    categoryService = new CategoryServiceImp(
      createCategoryUseCase,
      retrieveCategoriesUseCase,
      mapper,
      paginationMapper,
      sortingMapper
    );
  }

  @Test
  void createCategoryTest() {
    CreateCategoryDto createCategoryDto = new CreateCategoryDto("categoryName", "categoryDescription");
    Category expectedCategory = new Category(1L, "categoryName", "categoryDescription");
    CategoryDto expectedResult = new CategoryDto(1L, "categoryName", "categoryDescription");

    when(mapper.toDomain(createCategoryDto)).thenReturn(new Category(null, createCategoryDto.name(), createCategoryDto.description()));
    when(createCategoryUseCase.create(mapper.toDomain(createCategoryDto))).thenReturn(expectedCategory);
    when(mapper.toDto(expectedCategory)).thenReturn(expectedResult);

    CategoryDto categoryCreated = categoryService.create(createCategoryDto);

    assertEquals(expectedResult, categoryCreated);

    verify(createCategoryUseCase, times(1)).create(mapper.toDomain(createCategoryDto));
    verify(mapper, times(1)).toDto(expectedCategory);
  }

  @Test
  void retrieveCategoriesTest() {
    PaginationDto paginationDto = new PaginationDto(0, 10);
    SortingDto sortingDto = new SortingDto("name", "ASC");

    Pagination pagination = new Pagination(0, 10);
    Sorting sorting = new Sorting("name", "ASC");

    Category category1 = new Category(1L, "First", "categoryDescription1");
    Category category2 = new Category(2L, "Second", "categoryDescription2");
    Paginated<Category> paginatedCategories = new Paginated<>(List.of(category1, category2), 0L, 2L, 1L);

    // Preparar los DTOs de salida esperados
    CategoryDto categoryDto1 = new CategoryDto(1L, "First", "categoryDescription1");
    CategoryDto categoryDto2 = new CategoryDto(2L, "Second", "categoryDescription2");
    Paginated<CategoryDto> expectedPaginatedCategoryDto = new Paginated<>(List.of(categoryDto1, categoryDto2), 0L, 2L, 1L);

    when(paginationMapper.toDomain(paginationDto)).thenReturn(pagination);
    when(sortingMapper.toDomain(sortingDto)).thenReturn(sorting);
    when(retrieveCategoriesUseCase.retrieveCategories(pagination, sorting)).thenReturn(paginatedCategories);
    when(mapper.toDtoPaginated(paginatedCategories)).thenReturn(expectedPaginatedCategoryDto);

    Paginated<CategoryDto> result = categoryService.retrieveCategories(paginationDto, sortingDto);

    assertEquals(expectedPaginatedCategoryDto, result);
    assertEquals(1L, result.getData().get(0).id());

    verify(paginationMapper).toDomain(paginationDto);
    verify(sortingMapper).toDomain(sortingDto);
    verify(retrieveCategoriesUseCase).retrieveCategories(pagination, sorting);
    verify(mapper).toDtoPaginated(paginatedCategories);
  }

}
