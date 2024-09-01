package com.emazon.msvc.stock.msvcstock.domain.models;

import com.emazon.msvc.stock.msvcstock.domain.exceptions.InvalidInputException;
import com.emazon.msvc.stock.msvcstock.domain.models.Pagination;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PaginationTest {
  private Pagination pagination;

  @BeforeEach
  void setUp(){
    pagination = new Pagination();
  }

  @Test
  void testValidPagination(){
    assertDoesNotThrow(() -> {
      pagination.setPage(1);
      pagination.setSize(10);
    });
  }

  @Test
  void testSetNullPage(){
    assertThrows(InvalidInputException.class,() -> pagination.setPage(null));
  }
  @Test
  void testSetNegativePage(){
    assertThrows(InvalidInputException.class,() -> pagination.setPage(-1));
  }

  @Test
  void testSetNullSize(){
    assertThrows(InvalidInputException.class,() -> pagination.setSize(null));
  }
  @Test
  void testSetNegativeSize(){
    assertThrows(InvalidInputException.class,() -> pagination.setSize(-1));
  }
}
