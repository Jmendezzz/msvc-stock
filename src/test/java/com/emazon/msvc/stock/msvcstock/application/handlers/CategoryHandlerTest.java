package com.emazon.msvc.stock.msvcstock.application.handlers;

import com.emazon.msvc.stock.msvcstock.application.dtos.category.CategoryResponseDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.category.CreateCategoryRequestDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.pagination.PaginationDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.sorting.SortingDto;
import com.emazon.msvc.stock.msvcstock.application.mappers.CategoryMapper;
import com.emazon.msvc.stock.msvcstock.application.mappers.PaginationMapper;
import com.emazon.msvc.stock.msvcstock.application.mappers.SortingMapper;
import com.emazon.msvc.stock.msvcstock.application.handlers.imp.CategoryHandlerImp;
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
class CategoryHandlerTest {

  private CategoryHandler categoryHandler;
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
    categoryHandler = new CategoryHandlerImp(
      createCategoryUseCase,
      retrieveCategoriesUseCase,
      mapper,
      paginationMapper,
      sortingMapper
    );
  }

  @Test
  void createCategoryTest() {
    CreateCategoryRequestDto createCategoryRequestDto = new CreateCategoryRequestDto("categoryName", "categoryDescription");
    Category expectedCategory = new Category(1L, "categoryName", "categoryDescription");
    CategoryResponseDto expectedResult = new CategoryResponseDto(1L, "categoryName", "categoryDescription");

    when(mapper.toDomain(createCategoryRequestDto)).thenReturn(new Category(null, createCategoryRequestDto.name(), createCategoryRequestDto.description()));
    when(createCategoryUseCase.create(mapper.toDomain(createCategoryRequestDto))).thenReturn(expectedCategory);
    when(mapper.toDto(expectedCategory)).thenReturn(expectedResult);

    CategoryResponseDto categoryCreated = categoryHandler.create(createCategoryRequestDto);

    assertEquals(expectedResult, categoryCreated);

    verify(createCategoryUseCase, times(1)).create(mapper.toDomain(createCategoryRequestDto));
    verify(mapper, times(1)).toDto(expectedCategory);
  }

  @Test
  void retrieveCategoriesTest() {
    PaginationDto paginationDto = new PaginationDto(0, 10);
    SortingDto sortingDto = new SortingDto("name", "ASC");

    // Prepare input mapped to domain
    Pagination pagination = new Pagination(0, 10);
    Sorting sorting = new Sorting("name", "ASC");

    Category category1 = new Category(1L, "First", "categoryDescription1");
    Category category2 = new Category(2L, "Second", "categoryDescription2");
    Paginated<Category> paginatedCategories = new Paginated<>(List.of(category1, category2), 0L, 2L, 1L);

    // Prepare expected result
    CategoryResponseDto categoryResponseDto1 = new CategoryResponseDto(1L, "First", "categoryDescription1");
    CategoryResponseDto categoryResponseDto2 = new CategoryResponseDto(2L, "Second", "categoryDescription2");
    Paginated<CategoryResponseDto> expectedPaginatedCategoryDto = new Paginated<>(List.of(categoryResponseDto1, categoryResponseDto2), 0L, 2L, 1L);

    when(paginationMapper.toDomain(paginationDto)).thenReturn(pagination);
    when(sortingMapper.toDomain(sortingDto)).thenReturn(sorting);
    when(retrieveCategoriesUseCase.retrieveCategories(pagination, sorting)).thenReturn(paginatedCategories);
    when(mapper.toDtoPaginated(paginatedCategories)).thenReturn(expectedPaginatedCategoryDto);

    Paginated<CategoryResponseDto> result = categoryHandler.retrieveCategories(paginationDto, sortingDto);

    assertEquals(expectedPaginatedCategoryDto, result);
    assertEquals(1L, result.getData().get(0).id());

    verify(paginationMapper).toDomain(paginationDto);
    verify(sortingMapper).toDomain(sortingDto);
    verify(retrieveCategoriesUseCase).retrieveCategories(pagination, sorting);
    verify(mapper).toDtoPaginated(paginatedCategories);
  }

}
