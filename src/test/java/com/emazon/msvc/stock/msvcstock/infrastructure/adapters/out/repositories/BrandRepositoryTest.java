package com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.repositories;

import com.emazon.msvc.stock.msvcstock.domain.models.Brand;
import com.emazon.msvc.stock.msvcstock.domain.models.Paginated;
import com.emazon.msvc.stock.msvcstock.domain.models.Pagination;
import com.emazon.msvc.stock.msvcstock.domain.models.Sorting;
import com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.entities.BrandEntity;
import com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.mappers.DboBrandMapper;
import com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.repositories.jpa.JpaBrandRepository;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BrandRepositoryTest {

  @Mock
  private JpaBrandRepository jpaBrandRepository;

  @Mock
  private DboBrandMapper mapper;

  @InjectMocks
  private BrandRepositoryImp brandRepositoryImp;

  @Test
  void testSave() {
    // Arrange
    Brand brand = new Brand(); // create a test Brand
    BrandEntity brandEntity = new BrandEntity(); // create a test BrandEntity
    when(mapper.toEntity(brand)).thenReturn(brandEntity);
    when(jpaBrandRepository.save(brandEntity)).thenReturn(brandEntity);
    when(mapper.toDomain(brandEntity)).thenReturn(brand);

    // Act
    Brand result = brandRepositoryImp.save(brand);

    // Assert
    assertEquals(brand, result);
    verify(mapper).toEntity(brand);
    verify(jpaBrandRepository).save(brandEntity);
    verify(mapper).toDomain(brandEntity);
  }

  @Test
  void testFindByName() {
    // Arrange
    Brand brand = new Brand(1L, "Test", "Test"); // create a test Brand
    BrandEntity brandEntity = new BrandEntity(1L, "Test", "Test"); // create a test BrandEntity
    String brandName = "brandName";

    // Act
    when(jpaBrandRepository.findByName(brandName)).thenReturn(Optional.of(brandEntity));
    when(mapper.toDomain(brandEntity)).thenReturn(brand);
    // Assert
    Optional<Brand> result = brandRepositoryImp.findByName(brandName);
    assertTrue(result.isPresent());
    assertEquals(brand, result.get());
    verify(jpaBrandRepository).findByName(brandName);
    verify(mapper).toDomain(brandEntity);
  }

  @Test
  void testFindByNameNotFound() {
    // Arrange
    String brandName = "brandName";

    // Act
    when(jpaBrandRepository.findByName(brandName)).thenReturn(Optional.empty());

    // Assert
    Optional<Brand> result = brandRepositoryImp.findByName(brandName);
    assertTrue(result.isEmpty());
    verify(jpaBrandRepository).findByName(brandName);
    verifyNoInteractions(mapper);
  }

  @Test
  void testFindById() {
    // Arrange
    Brand brand = new Brand(1L, "Test", "Test"); // create a test Brand
    BrandEntity brandEntity = new BrandEntity(1L, "Test", "Test"); // create a test BrandEntity
    Long id = 1L;

    // Act
    when(jpaBrandRepository.findById(id)).thenReturn(Optional.of(brandEntity));
    when(mapper.toDomain(brandEntity)).thenReturn(brand);
    // Assert
    Optional<Brand> result = brandRepositoryImp.findById(id);
    assertTrue(result.isPresent());
    assertEquals(brand, result.get());
    verify(jpaBrandRepository).findById(id);
    verify(mapper).toDomain(brandEntity);
  }

  @Test
  void testFindByIdNotFound() {
    // Arrange
    Long id = 1L;

    // Act
    when(jpaBrandRepository.findById(id)).thenReturn(Optional.empty());

    // Assert
    Optional<Brand> result = brandRepositoryImp.findById(id);
    assertTrue(result.isEmpty());
    verify(jpaBrandRepository).findById(id);
    verifyNoInteractions(mapper);
  }

  @Test
  void testFindAll() {
    // Arrange
    Brand brand = new Brand(1L, "Test", "Test"); // create a test Brand
    BrandEntity brandEntity = new BrandEntity(1L, "Test", "Test"); // create a test BrandEntity
    Pagination pagination = new Pagination(1, 1);
    Sorting sorting = new Sorting("name", "ASC");

    // Create a PageRequest object matching the one used in the method
    Pageable pageable = PageRequest.of(pagination.getPage(), pagination.getSize(), Sort.by(Sort.Direction.fromString(sorting.getDirection().name()), "name"));

    // Mock the repository and mapper
    when(jpaBrandRepository.findAll(pageable)).thenReturn(new PageImpl<>(List.of(brandEntity), pageable, 1));
    when(mapper.toDomainPaginated(any())).thenReturn(new Paginated<>(List.of(brand), 1L, 1L, 1L));

    // Act
    Paginated<Brand> result = brandRepositoryImp.findAll(pagination, sorting);

    // Assert
    assertEquals(1, result.getTotalPages());
    assertEquals(1, result.getTotalItems());
    assertEquals(List.of(brand), result.getData());
    verify(jpaBrandRepository).findAll(pageable);
    verify(mapper).toDomainPaginated(any());
  }
}