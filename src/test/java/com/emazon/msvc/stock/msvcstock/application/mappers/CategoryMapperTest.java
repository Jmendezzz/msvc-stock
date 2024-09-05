package com.emazon.msvc.stock.msvcstock.application.mappers;

import com.emazon.msvc.stock.msvcstock.application.dtos.category.CategoryResponseDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.category.CreateCategoryRequestDto;
import com.emazon.msvc.stock.msvcstock.domain.models.Category;
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
class CategoryMapperTest {
  CategoryMapper categoryMapper;

  @BeforeEach
  void setUp() {
    categoryMapper = Mappers.getMapper(CategoryMapper.class);
  }

  @Test
  void testMapCategoryToCategoryDto() {
    CategoryResponseDto expectedCategoryResponseDto = new CategoryResponseDto(1L, "categoryName", "categoryDescription");
    Category category = new Category(1L, "categoryName", "categoryDescription");
    CategoryResponseDto categoryResponseDto = categoryMapper.toDto(category);

    assertEquals(expectedCategoryResponseDto.id(), categoryResponseDto.id());
    assertEquals(expectedCategoryResponseDto.name(), categoryResponseDto.name());
    assertEquals(expectedCategoryResponseDto.description(), categoryResponseDto.description());
  }
  @Test
  void testMapNullCategoryToCategoryDto() {
    Category category = null;
    assertNull(categoryMapper.toDto(category));
  }

  @Test
  void testCreateCategoryDtoToCategory(){
    Category expectedCategory = new Category(null, "categoryName", "categoryDescription");
    CreateCategoryRequestDto createCategoryRequestDto = new CreateCategoryRequestDto("categoryName", "categoryDescription");
    Category category = categoryMapper.toDomain(createCategoryRequestDto);

    assertEquals(expectedCategory.getId(), category.getId());
    assertEquals(expectedCategory.getName(), category.getName());
    assertEquals(expectedCategory.getDescription(), category.getDescription());
  }
  @Test
  void testCreateNullCategoryDtoToCategory(){
    CreateCategoryRequestDto createCategoryRequestDto = null;
    assertNull(categoryMapper.toDomain(createCategoryRequestDto));
  }

  @Test
  void testListCategoryToCategoryDto(){
    CategoryResponseDto expectedCategoryResponseDto = new CategoryResponseDto(1L, "categoryName", "categoryDescription");
    Category category = new Category(1L, "categoryName", "categoryDescription");
    List<Category> categories = new ArrayList<>();
    categories.add(category);
    List<CategoryResponseDto> categoryResponseDtos = categoryMapper.toDto(categories);

    assertEquals(expectedCategoryResponseDto.id(), categoryResponseDtos.get(0).id());
    assertEquals(expectedCategoryResponseDto.name(), categoryResponseDtos.get(0).name());
    assertEquals(expectedCategoryResponseDto.description(), categoryResponseDtos.get(0).description());
  }
  @Test
  void testListNullCategoryToCategoryDto(){
    List<Category> categories = null;
    assertNull(categoryMapper.toDto(categories));
  }
  @Test
  void testPaginatedCategoryToPaginatedCategoryDto(){
    CategoryResponseDto expectedCategoryResponseDto = new CategoryResponseDto(1L, "categoryName", "categoryDescription");
    Category category = new Category(1L, "categoryName", "categoryDescription");
    List<Category> categories = new ArrayList<>();
    categories.add(category);
    Paginated<Category> paginated = new Paginated<>(categories, 1L, 1L, 1L);
    Paginated<CategoryResponseDto> paginatedDto = categoryMapper.toDtoPaginated(paginated);

    assertEquals(expectedCategoryResponseDto.id(), paginatedDto.getData().get(0).id());
    assertEquals(expectedCategoryResponseDto.name(), paginatedDto.getData().get(0).name());
    assertEquals(expectedCategoryResponseDto.description(), paginatedDto.getData().get(0).description());
    assertEquals(paginated.getTotalItems(), paginatedDto.getTotalItems());
    assertEquals(paginated.getTotalPages(), paginatedDto.getTotalPages());
    assertEquals(paginated.getCurrentPage(), paginatedDto.getCurrentPage());
  }
  @Test
  void testPaginatedNullCategoryToPaginatedCategoryDto(){
    Paginated<Category> paginated = null;
    assertNull(categoryMapper.toDtoPaginated(paginated));
  }
}
