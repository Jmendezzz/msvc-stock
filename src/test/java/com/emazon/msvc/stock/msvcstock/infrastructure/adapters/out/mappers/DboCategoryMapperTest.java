package com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.mappers;

import com.emazon.msvc.stock.msvcstock.domain.models.Category;
import com.emazon.msvc.stock.msvcstock.domain.models.Paginated;
import com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.entities.CategoryEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class DboCategoryMapperTest {
  private DboCategoryMapper dboCategoryMapper;

  @BeforeEach
  public void setUp() {
    dboCategoryMapper = Mappers.getMapper(DboCategoryMapper.class);
  }

  @Test
  void testMapCategoryToDboCategory() {
    Category category = new Category(1L, "categoryName", "categoryDescription");
    CategoryEntity dboCategory = dboCategoryMapper.toEntity(category);

    assertEquals(category.getId(), dboCategory.getId());
    assertEquals(category.getName(), dboCategory.getName());
    assertEquals(category.getDescription(), dboCategory.getDescription());
  }

  @Test
  void testMapDboCategoryToCategory() {
    CategoryEntity dboCategory = new CategoryEntity(1L, "categoryName", "categoryDescription");
    Category category = dboCategoryMapper.toDomain(dboCategory);

    assertEquals(dboCategory.getId(), category.getId());
    assertEquals(dboCategory.getName(), category.getName());
    assertEquals(dboCategory.getDescription(), category.getDescription());
  }

  @Test
  void testMapDboCategoryListToCategory(){
    List<CategoryEntity> dboCategories = List.of(new CategoryEntity(1L, "categoryName", "categoryDescription"));
    List<Category> categories = dboCategoryMapper.toDomain(dboCategories);

    assertEquals(dboCategories.get(0).getId(), categories.get(0).getId());
    assertEquals(dboCategories.get(0).getName(), categories.get(0).getName());
    assertEquals(dboCategories.get(0).getDescription(), categories.get(0).getDescription());
    assertEquals(dboCategories.size(), categories.size());
  }

  @Test
  void toDomainSet(){
    List<CategoryEntity> dboCategories = List.of(new CategoryEntity(1L, "categoryName", "categoryDescription"));

    Set<Category> categories = dboCategoryMapper.toDomainSet(dboCategories);

    assertEquals(dboCategories.get(0).getId(), categories.iterator().next().getId());
    assertEquals(dboCategories.get(0).getName(), categories.iterator().next().getName());
    assertEquals(dboCategories.get(0).getDescription(), categories.iterator().next().getDescription());
  }

  @Test
  void toDomainPaginated(){
    CategoryEntity dboCategory = new CategoryEntity(1L, "categoryName", "categoryDescription");
    Paginated<Category> paginated = dboCategoryMapper.toDomainPaginated(new PageImpl<>(List.of(dboCategory)));

    assertEquals(dboCategory.getId(), paginated.getData().get(0).getId());
    assertEquals(dboCategory.getName(), paginated.getData().get(0).getName());
    assertEquals(dboCategory.getDescription(), paginated.getData().get(0).getDescription());
  }

}
