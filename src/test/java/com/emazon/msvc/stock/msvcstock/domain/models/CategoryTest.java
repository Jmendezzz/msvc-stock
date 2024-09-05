package com.emazon.msvc.stock.msvcstock.domain.models;

import com.emazon.msvc.stock.msvcstock.domain.exceptions.InvalidInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CategoryTest {

  private Category category;

  @BeforeEach
  void setUp(){
    category = new Category();
  }

  @Test
  void testCreateValidCategory() {
    assertDoesNotThrow(() -> {
     new Category(1L, "ValidName", "ValidDescription");
    });
  }

  @Test
  void testSetNullName(){
    assertThrows(InvalidInputException.class, () -> category.setName(null));
  }
  @Test
  void testSetEmptyName(){
    assertThrows(InvalidInputException.class, () -> category.setName(""));
  }
  @Test
  void testShortLengthName(){
    assertThrows(InvalidInputException.class, () -> category.setName("a"));
  }

  @Test
  void testLongLengthName(){
    String longName = "a".repeat(51);
    assertThrows(InvalidInputException.class, () -> category.setName(longName));
  }
  @Test
  void testSetNullDescription(){
    assertThrows(InvalidInputException.class, () -> category.setDescription(null));
  }
  @Test
  void testSetEmptyDescription(){
    assertThrows(InvalidInputException.class, () -> category.setDescription(""));
  }

  @Test
  void testShortLengthDescription(){
    assertThrows(InvalidInputException.class, () -> category.setDescription("a"));
  }

  @Test
  void testLongLengthDescription(){
    String longDescription = "a".repeat(121);
    assertThrows(InvalidInputException.class, () -> category.setDescription(longDescription));
  }

}
