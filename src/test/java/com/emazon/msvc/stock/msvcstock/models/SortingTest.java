package com.emazon.msvc.stock.msvcstock.models;

import com.emazon.msvc.stock.msvcstock.domain.exceptions.InvalidInputException;
import com.emazon.msvc.stock.msvcstock.domain.models.Sorting;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SortingTest {

  private Sorting sorting;

  @BeforeEach
  void setUp(){
    sorting = new Sorting();
  }

  @Test
  void testValidSorting(){
    assertDoesNotThrow(() -> {
      sorting.setSortBy("name");
      sorting.setDirection("ASC");
    });
  }
  @Test
  void testSetNullSortBy(){
    assertThrows(InvalidInputException.class, () -> sorting.setSortBy(null));
  }
  @Test
  void testSetEmptySortBy(){
    assertThrows(InvalidInputException.class, () -> sorting.setSortBy(""));
  }

  @Test
  void testSetNullDirection(){
    assertThrows(InvalidInputException.class, () -> sorting.setDirection(null));
  }
  @Test
  void testSetEmptyDirection(){
    assertThrows(InvalidInputException.class, () -> sorting.setDirection(""));
  }
  @Test
  void testSetInvalidDirection(){
    assertThrows(InvalidInputException.class, () -> sorting.setDirection("invalid"));
  }
}
