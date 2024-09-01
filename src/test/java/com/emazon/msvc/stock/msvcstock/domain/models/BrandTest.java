package com.emazon.msvc.stock.msvcstock.domain.models;

import com.emazon.msvc.stock.msvcstock.domain.exceptions.InvalidInputException;
import com.emazon.msvc.stock.msvcstock.domain.models.Brand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BrandTest {
  private Brand brand;

  @BeforeEach
  void setUp(){
    brand = new Brand();
  }

  @Test
  void testCreateValidBrand() {
    assertDoesNotThrow(() -> {
     new Brand(1L, "ValidName", "ValidDescription");
    });
  }

  @Test
  void testSetNullName(){
    assertThrows(InvalidInputException.class,() -> brand.setName(null));
  }
  @Test
  void testSetEmptyName(){
    assertThrows(InvalidInputException.class,() -> brand.setName(""));
  }

  @Test
  void testShortLengthName(){
    assertThrows(InvalidInputException.class,() -> brand.setName("a"));
  }
  @Test
  void testLongLengthName(){
    String longName = "a".repeat(51);
    assertThrows(InvalidInputException.class,() -> brand.setName(longName));
  }
  @Test
  void testSetNullDescription(){
    assertThrows(InvalidInputException.class,() -> brand.setDescription(null));
  }
  @Test
  void testSetEmptyDescription(){
    assertThrows(InvalidInputException.class,() -> brand.setDescription(""));
  }
  @Test
  void testShortLengthDescription(){
    assertThrows(InvalidInputException.class,() -> brand.setDescription("a"));
  }
  @Test
  void testLongLengthDescription(){
    String longDescription = "a".repeat(121);
    assertThrows(InvalidInputException.class,() -> brand.setDescription(longDescription));
  }
}
