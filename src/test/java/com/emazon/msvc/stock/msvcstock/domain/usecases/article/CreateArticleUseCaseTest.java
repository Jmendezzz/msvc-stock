package com.emazon.msvc.stock.msvcstock.domain.usecases.article;

import com.emazon.msvc.stock.msvcstock.domain.exceptions.article.ArticleBrandNotFoundException;
import com.emazon.msvc.stock.msvcstock.domain.exceptions.article.ArticleCategoryNotFoundException;
import com.emazon.msvc.stock.msvcstock.domain.models.Article;
import com.emazon.msvc.stock.msvcstock.domain.models.Brand;
import com.emazon.msvc.stock.msvcstock.domain.models.Category;
import com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.article.CreateArticleUseCase;
import com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.brand.RetrieveBrandUseCase;
import com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.category.RetrieveCategoryUseCase;
import com.emazon.msvc.stock.msvcstock.domain.ports.out.repositories.ArticleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateArticleUseCaseTest {

  private CreateArticleUseCase createArticleUseCase;

  @Mock
  private ArticleRepository articleRepository;
  @Mock
  private RetrieveCategoryUseCase retrieveCategoryUseCase;
  @Mock
  private RetrieveBrandUseCase retrieveBrandUseCase;

  private Brand brand;
  private Category category1;
  private Category category2;
  private Article article;

  @BeforeEach
  public void setUp() {
    createArticleUseCase = new CreateArticleUseCaseImp(articleRepository, retrieveCategoryUseCase, retrieveBrandUseCase);

    brand = new Brand(1L, "BrandName", "BrandDescription");
    category1 = new Category(1L, "Category1", "CategoryDescription");
    category2 = new Category(2L, "Category2", "CategoryDescription");
    article = new Article(null, "ArticleName", "ArticleDescription", 99.0, 10, brand, Set.of(category1, category2));
  }

  @Test
  void createArticleTest() {
    when(retrieveBrandUseCase.retrieveBrandById(1L)).thenReturn(Optional.of(brand));
    when(retrieveCategoryUseCase.retrieveCategoriesByIds(Set.of(1L, 2L))).thenReturn(Set.of(category1, category2));
    when(articleRepository.save(article)).thenReturn(article);

    Article savedArticle = createArticleUseCase.create(article);

    assertEquals(article, savedArticle);
  }

  @Test
  void createArticleBrandNotFoundTest() {
    when(retrieveCategoryUseCase.retrieveCategoriesByIds(Set.of(1L, 2L))).thenReturn(Set.of(category1,category2));
    when(retrieveBrandUseCase.retrieveBrandById(1L)).thenReturn(Optional.empty());

    assertThrows(ArticleBrandNotFoundException.class, () -> createArticleUseCase.create(article));
  }

  @Test
  void createArticleCategoriesNotFoundTest() {
    when(retrieveCategoryUseCase.retrieveCategoriesByIds(Set.of(1L, 2L))).thenReturn(Set.of(category1));

    assertThrows(ArticleCategoryNotFoundException.class, () -> createArticleUseCase.create(article));
  }
}
