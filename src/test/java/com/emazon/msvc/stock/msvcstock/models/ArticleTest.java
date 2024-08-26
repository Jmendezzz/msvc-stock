package com.emazon.msvc.stock.msvcstock.models;

import com.emazon.msvc.stock.msvcstock.domain.exceptions.InvalidInputException;
import com.emazon.msvc.stock.msvcstock.domain.models.Article;
import com.emazon.msvc.stock.msvcstock.domain.models.Brand;
import com.emazon.msvc.stock.msvcstock.domain.models.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ArticleTest {
  private Article article;
  @BeforeEach
  void setUp(){
    article = new Article();
  }
  @Test
  void testCreateValidArticle() {
    Brand brand = new Brand(1L, "BrandName", "BrandDescription");
    Category category = new Category(1L, "Category1", "CategoryDescription", LocalDateTime.now());
    assertDoesNotThrow(() -> {
     new Article(1L, "ValidName", "ValidDescription", 99.0, 10, brand, Set.of(category));
    });
  }

  @Test
  void testSetNullName(){
    assertThrows(InvalidInputException.class,() -> article.setName(null));
  }
  @Test
  void testSetEmptyName(){
    assertThrows(InvalidInputException.class,() -> article.setName(""));
  }
  @Test
  void testShortLengthName(){
    assertThrows(InvalidInputException.class,() -> article.setName("a"));
  }
  @Test
  void testLongLengthName(){
    String longName = "a".repeat(51);
    assertThrows(InvalidInputException.class,() -> article.setName(longName));
  }

  @Test
  void testSetNullDescription(){
    assertThrows(InvalidInputException.class,() -> article.setDescription(null));
  }
  @Test
  void testSetEmptyDescription(){
    assertThrows(InvalidInputException.class,() -> article.setDescription(""));
  }
  @Test
  void testShortLengthDescription(){
    assertThrows(InvalidInputException.class,() -> article.setDescription("a"));
  }
  @Test
  void testLongLengthDescription(){
    String longDescription = "a".repeat(256);
    assertThrows(InvalidInputException.class,() -> article.setDescription(longDescription));
  }
  @Test
  void testSetNullPrice(){
    assertThrows(InvalidInputException.class,() -> article.setPrice(null));
  }
  @Test
  void testSetNegativePrice(){
    assertThrows(InvalidInputException.class,() -> article.setPrice(-1.0));
  }

  @Test
  void testSetNullStock(){
    assertThrows(InvalidInputException.class,() -> article.setStock(null));
  }
  @Test
  void testSetNegativeStock(){
    assertThrows(InvalidInputException.class,() -> article.setStock(-1));
  }
  @Test
  void testSetNullBrand(){
    assertThrows(InvalidInputException.class,() -> article.setBrand(null));
  }
  @Test
  void testSetNullCategories(){
    assertThrows(InvalidInputException.class,() -> article.setCategories(null));
  }
  @Test
  void testSetEmptyCategories(){
    Set<Category> categories = Set.of();
    assertThrows(InvalidInputException.class,() -> article.setCategories(categories));
  }
  @Test
  void testSetTooManyCategories(){
    Set<Category> categories = Set.of(
      new Category(1L, "Category1", "CategoryDescription", LocalDateTime.now()),
      new Category(2L, "Category2", "CategoryDescription", LocalDateTime.now()),
      new Category(3L, "Category3", "CategoryDescription", LocalDateTime.now()),
      new Category(4L, "Category4", "CategoryDescription", LocalDateTime.now())
    );
    assertThrows(InvalidInputException.class,() -> article.setCategories(categories));
  }
}
