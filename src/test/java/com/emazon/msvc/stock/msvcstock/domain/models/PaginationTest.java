package com.emazon.msvc.stock.msvcstock.domain.models;

import com.emazon.msvc.stock.msvcstock.domain.exceptions.InvalidInputException;
import com.emazon.msvc.stock.msvcstock.domain.models.Pagination;
import com.emazon.msvc.stock.msvcstock.domain.utils.constants.pagination.PaginationConstant;
import com.emazon.msvc.stock.msvcstock.domain.utils.constants.pagination.PaginationValidationConstant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
  void testSetDefaultPage(){
    Integer defaultPage = PaginationConstant.DEFAULT_PAGE;
    pagination.setPage(null);
    assertEquals(defaultPage,pagination.getPage());
  }
  @Test
  void testSetNegativePage(){
    assertThrows(InvalidInputException.class,() -> pagination.setPage(-1));
  }

  @Test
  void testSetDefaultSize(){
    Integer defaultSize = PaginationConstant.DEFAULT_SIZE;
    pagination.setSize(null);
    assertEquals(defaultSize,pagination.getSize());

  }
  @Test
  void testSetNegativeSize(){
    assertThrows(InvalidInputException.class,() -> pagination.setSize(-1));
  }
}
