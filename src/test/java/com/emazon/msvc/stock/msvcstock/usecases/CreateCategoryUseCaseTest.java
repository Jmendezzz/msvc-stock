package com.emazon.msvc.stock.msvcstock.usecases;

import com.emazon.msvc.stock.msvcstock.domain.exceptions.category.DuplicateCategoryNameException;
import com.emazon.msvc.stock.msvcstock.domain.models.Category;
import com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.CreateCategoryUseCase;
import com.emazon.msvc.stock.msvcstock.domain.ports.out.repositories.CategoryRepository;
import com.emazon.msvc.stock.msvcstock.domain.ports.usecases.CreateCategoryUseCaseImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class CreateCategoryUseCaseTest {
  private CreateCategoryUseCase createCategoryUseCase;

  @Mock
  private CategoryRepository categoryRepository;

  @BeforeEach
  public void setUp() {
    createCategoryUseCase = new CreateCategoryUseCaseImp(categoryRepository);
  }
  @Test
   void createCategoryTest() {
    Category category = new Category(1L, "categoryName", "categoryDescription", LocalDateTime.now());

    when(categoryRepository.findByName(category.getName())).thenReturn(Optional.empty());
    when(categoryRepository.save(category)).thenReturn(category);

    Category createdCategory = createCategoryUseCase.create(category);

    assertEquals(category, createdCategory);
  }
  @Test
  void createCategoryWithDuplicateNameTest() {
    Category category = new Category(1L, "categoryName", "categoryDescription", LocalDateTime.now());

    when(categoryRepository.findByName(category.getName())).thenReturn(Optional.of(category));

    assertThrows(DuplicateCategoryNameException.class, () -> createCategoryUseCase.create(category));
  }

}
