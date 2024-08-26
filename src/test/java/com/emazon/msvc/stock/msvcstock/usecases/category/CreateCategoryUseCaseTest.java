package com.emazon.msvc.stock.msvcstock.usecases.category;

import com.emazon.msvc.stock.msvcstock.domain.exceptions.InvalidInputException;
import com.emazon.msvc.stock.msvcstock.domain.exceptions.category.DuplicateCategoryNameException;
import com.emazon.msvc.stock.msvcstock.domain.models.Category;
import com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.category.CreateCategoryUseCase;
import com.emazon.msvc.stock.msvcstock.domain.ports.out.repositories.CategoryRepository;
import com.emazon.msvc.stock.msvcstock.domain.usecases.category.CreateCategoryUseCaseImp;
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
  void createCategoryWithEmptyNameTest() {
    assertThrows(InvalidInputException.class, () ->  new Category(1L, "", "categoryDescription", LocalDateTime.now()));
  }
  @Test
  void createCategoryWithInvalidNameLengthTest() {
    assertThrows(InvalidInputException.class, () ->  new Category(1L, "a", "categoryDescription", LocalDateTime.now()));
  }

  @Test
  void createCategoryWithEmptyDescriptionTest() {
    assertThrows(InvalidInputException.class, () ->  new Category(1L, "categoryName", "", LocalDateTime.now()));
  }

  @Test
  void createCategoryWithInvalidDescriptionLengthTest() {
    assertThrows(InvalidInputException.class, () ->  new Category(1L, "categoryName", "a", LocalDateTime.now()));
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
