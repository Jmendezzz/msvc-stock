package com.emazon.msvc.stock.msvcstock.application.mappers;

import com.emazon.msvc.stock.msvcstock.application.dtos.category.CategoryDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.category.CreateCategoryDto;
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
    CategoryDto expectedCategoryDto = new CategoryDto(1L, "categoryName", "categoryDescription");
    Category category = new Category(1L, "categoryName", "categoryDescription");
    CategoryDto categoryDto = categoryMapper.toDto(category);

    assertEquals(expectedCategoryDto.id(), categoryDto.id());
    assertEquals(expectedCategoryDto.name(), categoryDto.name());
    assertEquals(expectedCategoryDto.description(), categoryDto.description());
  }
  @Test
  void testMapNullCategoryToCategoryDto() {
    Category category = null;
    assertNull(categoryMapper.toDto(category));
  }

  @Test
  void testCreateCategoryDtoToCategory(){
    Category expectedCategory = new Category(null, "categoryName", "categoryDescription");
    CreateCategoryDto createCategoryDto = new CreateCategoryDto("categoryName", "categoryDescription");
    Category category = categoryMapper.toDomain(createCategoryDto);

    assertEquals(expectedCategory.getId(), category.getId());
    assertEquals(expectedCategory.getName(), category.getName());
    assertEquals(expectedCategory.getDescription(), category.getDescription());
  }
  @Test
  void testCreateNullCategoryDtoToCategory(){
    CreateCategoryDto createCategoryDto = null;
    assertNull(categoryMapper.toDomain(createCategoryDto));
  }

  @Test
  void testListCategoryToCategoryDto(){
    CategoryDto expectedCategoryDto = new CategoryDto(1L, "categoryName", "categoryDescription");
    Category category = new Category(1L, "categoryName", "categoryDescription");
    List<Category> categories = new ArrayList<>();
    categories.add(category);
    List<CategoryDto> categoryDtos = categoryMapper.toDto(categories);

    assertEquals(expectedCategoryDto.id(), categoryDtos.get(0).id());
    assertEquals(expectedCategoryDto.name(), categoryDtos.get(0).name());
    assertEquals(expectedCategoryDto.description(), categoryDtos.get(0).description());
  }
  @Test
  void testListNullCategoryToCategoryDto(){
    List<Category> categories = null;
    assertNull(categoryMapper.toDto(categories));
  }
  @Test
  void testPaginatedCategoryToPaginatedCategoryDto(){
    CategoryDto expectedCategoryDto = new CategoryDto(1L, "categoryName", "categoryDescription");
    Category category = new Category(1L, "categoryName", "categoryDescription");
    List<Category> categories = new ArrayList<>();
    categories.add(category);
    Paginated<Category> paginated = new Paginated<>(categories, 1L, 1L, 1L);
    Paginated<CategoryDto> paginatedDto = categoryMapper.toDtoPaginated(paginated);

    assertEquals(expectedCategoryDto.id(), paginatedDto.getData().get(0).id());
    assertEquals(expectedCategoryDto.name(), paginatedDto.getData().get(0).name());
    assertEquals(expectedCategoryDto.description(), paginatedDto.getData().get(0).description());
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
