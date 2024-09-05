package com.emazon.msvc.stock.msvcstock.domain.models;

import com.emazon.msvc.stock.msvcstock.domain.exceptions.InvalidInputException;
import com.emazon.msvc.stock.msvcstock.domain.utils.constants.sorting.SortingConstant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    sorting.setSortBy(null);
    assertNull(sorting.getSortBy());

  }

  @Test
  void testSetNullDefaultDirection(){
    sorting.setDirection(null);
    assertEquals(SortingConstant.SORTING_DEFAULT_DIRECTION.name() , sorting.getDirection().name());
  }
  @Test
  void testSetEmptyDefaultDirection(){
    sorting.setDirection("");
    assertEquals(SortingConstant.SORTING_DEFAULT_DIRECTION.name() , sorting.getDirection().name());
  }
  @Test
  void testSetInvalidDirection(){
    assertThrows(InvalidInputException.class, () -> sorting.setDirection("invalid"));
  }
}
