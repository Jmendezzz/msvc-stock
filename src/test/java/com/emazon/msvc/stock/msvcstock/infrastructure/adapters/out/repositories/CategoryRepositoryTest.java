package com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.repositories;

import com.emazon.msvc.stock.msvcstock.domain.models.Category;
import com.emazon.msvc.stock.msvcstock.domain.models.Paginated;
import com.emazon.msvc.stock.msvcstock.domain.models.Pagination;
import com.emazon.msvc.stock.msvcstock.domain.models.Sorting;
import com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.entities.CategoryEntity;
import com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.mappers.DboCategoryMapper;
import com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.repositories.jpa.JpaCategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

class CategoryRepositoryTest {
  @Mock
  private JpaCategoryRepository jpaCategoryRepository;

  @Mock
  private DboCategoryMapper mapper;

  @InjectMocks
  private CategoryRepositoryImp categoryRepositoryImp;

  @Test
  void save() {
    // Arrange
    Category category = new Category(); // create a test Category
    CategoryEntity categoryEntity = new CategoryEntity(); // create a test CategoryEntity
    when(mapper.toEntity(category)).thenReturn(categoryEntity);
    when(jpaCategoryRepository.save(categoryEntity)).thenReturn(categoryEntity);
    when(mapper.toDomain(categoryEntity)).thenReturn(category);

    // Act
    Category result = categoryRepositoryImp.save(category);

    // Assert
    assertEquals(category, result);
    verify(mapper).toEntity(category);
    verify(jpaCategoryRepository).save(categoryEntity);
    verify(mapper).toDomain(categoryEntity);
  }

  @Test
  void findByName() {
    // Arrange
    Category category = new Category(1L, "Test", "Test"); // create a test Category
    CategoryEntity categoryEntity = new CategoryEntity(1L, "Test", "Test"); // create a test CategoryEntity
    when(jpaCategoryRepository.findByName("Test")).thenReturn(java.util.Optional.of(categoryEntity));
    when(mapper.toDomain(categoryEntity)).thenReturn(category);

    // Act
    var result = categoryRepositoryImp.findByName("Test");

    // Assert
    assertEquals(category, result.get());
    verify(jpaCategoryRepository).findByName("Test");
    verify(mapper).toDomain(categoryEntity);
  }

  @Test
  void testFindByNameNotFound() {
    // Arrange
    String categoryName = "categoryName";

    // Act
    when(jpaCategoryRepository.findByName(categoryName)).thenReturn(java.util.Optional.empty());

    // Assert
    var result = categoryRepositoryImp.findByName(categoryName);
    assertEquals(java.util.Optional.empty(), result);
    verify(jpaCategoryRepository).findByName(categoryName);
  }

  @Test
  void testFindAll() {
    // Arrange
    Pagination pagination = new Pagination(1, 1);
    Sorting sorting = new Sorting("name", "ASC");

    // Create a PageRequest object matching the one used in the method
    Pageable pageable = PageRequest.of(pagination.getPage(), pagination.getSize(), Sort.by(Sort.Direction.fromString(sorting.getDirection().name()), sorting.getSortBy()));

    Category category = new Category(1L, "Test", "Test"); // create a test Category
    CategoryEntity categoryEntity = new CategoryEntity(1L, "Test", "Test"); // create a test CategoryEntity

    // Mock the repository and mapper
    when(jpaCategoryRepository.findAll(pageable)).thenReturn(new PageImpl<>(List.of(categoryEntity), pageable, 1));
    when(mapper.toDomainPaginated(any())).thenReturn(new Paginated<>(List.of(category), 1L, 1L, 1L));

    // Act
    Paginated<Category> result = categoryRepositoryImp.findAll(pagination, sorting);

    // Assert
    assertEquals(1, result.getTotalPages());
    assertEquals(1, result.getTotalItems());
    assertEquals(List.of(category), result.getData());
    verify(jpaCategoryRepository).findAll(pageable);
    verify(mapper).toDomainPaginated(any());
  }

  @Test
  void testFindAllById(){
    // Arrange
    Set<Long> ids = Set.of(1L, 2L, 3L);
    Category category = new Category(1L, "Test", "Test"); // create a test Category
    CategoryEntity categoryEntity = new CategoryEntity(1L, "Test", "Test"); // create a test CategoryEntity

    // Mock the repository and mapper
    when(jpaCategoryRepository.findAllById(ids)).thenReturn(List.of(categoryEntity));
    when(mapper.toDomainSet(List.of(categoryEntity))).thenReturn(Set.of(category));

    // Act
    Set<Category> result = categoryRepositoryImp.findAllById(ids);

    // Assert
    assertEquals(Set.of(category), result);
    verify(jpaCategoryRepository).findAllById(ids);
    verify(mapper).toDomainSet(List.of(categoryEntity));
  }

  @Test
  void testFindAllByIdEmpty(){
    // Arrange
    Set<Long> ids = Set.of(1L, 2L, 3L);

    // Mock the repository and mapper
    when(jpaCategoryRepository.findAllById(ids)).thenReturn(List.of());
    when(mapper.toDomainSet(List.of())).thenReturn(Set.of());

    // Act
    Set<Category> result = categoryRepositoryImp.findAllById(ids);

    // Assert
    assertEquals(Set.of(), result);
    verify(jpaCategoryRepository).findAllById(ids);
    verify(mapper).toDomainSet(List.of());
  }


}
