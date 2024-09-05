package com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.mappers;

import com.emazon.msvc.stock.msvcstock.domain.models.Article;
import com.emazon.msvc.stock.msvcstock.domain.models.Brand;
import com.emazon.msvc.stock.msvcstock.domain.models.Category;
import com.emazon.msvc.stock.msvcstock.domain.models.Paginated;
import com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.entities.ArticleEntity;
import com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.entities.BrandEntity;
import com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.entities.CategoryEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class DboArticleMapperTest {
  private DboArticleMapper dboArticleMapper;

  @BeforeEach
  public void setUp() {
    dboArticleMapper = new DboArticleMapperImpl(new DboBrandMapperImpl(), new DboCategoryMapperImpl());
  }

  @Test
  void testMapArticleToDboArticle() {
    Article article = new Article(1L, "articleName", "articleDescription", 10.0, 1, new Brand(1L, "brandName", "brandDescription"), Set.of( new Category(1L, "categoryName", "categoryDescription")));
    ArticleEntity dboArticle = dboArticleMapper.toEntity(article);

    assertEquals(article.getId(), dboArticle.getId());
    assertEquals(article.getName(), dboArticle.getName());
    assertEquals(article.getDescription(), dboArticle.getDescription());
    assertEquals(article.getPrice(), dboArticle.getPrice());
    assertEquals(article.getStock(), dboArticle.getStock());
    assertEquals(article.getBrand().getId(), dboArticle.getBrand().getId());
    assertEquals(article.getBrand().getName(), dboArticle.getBrand().getName());
    assertEquals(article.getBrand().getDescription(), dboArticle.getBrand().getDescription());
    assertEquals(article.getCategories().size(), dboArticle.getCategories().size());
  }
  @Test
  void testMapDboArticleToArticle() {
    ArticleEntity dboArticle = new ArticleEntity(1L, "articleName", "articleDescription", 10.0, 1, new BrandEntity(1L, "brandName", "brandDescription"), Set.of(new CategoryEntity(1L, "categoryName", "categoryDescription")));
    Article article = dboArticleMapper.toDomain(dboArticle);

    assertEquals(dboArticle.getId(), article.getId());
    assertEquals(dboArticle.getName(), article.getName());
    assertEquals(dboArticle.getDescription(), article.getDescription());
    assertEquals(dboArticle.getPrice(), article.getPrice());
    assertEquals(dboArticle.getStock(), article.getStock());
    assertEquals(dboArticle.getBrand().getId(), article.getBrand().getId());
    assertEquals(dboArticle.getBrand().getName(), article.getBrand().getName());
    assertEquals(dboArticle.getBrand().getDescription(), article.getBrand().getDescription());
    assertEquals(dboArticle.getCategories().size(), article.getCategories().size());
  }

  @Test
  void testMapDboArticleListToArticle(){
    ArticleEntity dboArticle = new ArticleEntity(1L, "articleName", "articleDescription", 10.0, 1, new BrandEntity(1L, "brandName", "brandDescription"), Set.of(new CategoryEntity(1L, "categoryName", "categoryDescription")));
    assertEquals(1, dboArticleMapper.toDomain(List.of(dboArticle)).size());
  }


  @Test
  void testToDomainPaginated(){
    ArticleEntity dboArticle = new ArticleEntity(1L, "articleName", "articleDescription", 10.0, 1, new BrandEntity(1L, "brandName", "brandDescription"), Set.of(new CategoryEntity(1L, "categoryName", "categoryDescription")));
    Paginated<Article> paginated = dboArticleMapper.toDomainPaginated(new PageImpl<>(List.of(dboArticle)));

    assertEquals(dboArticle.getId(), paginated.getData().get(0).getId());
    assertEquals(dboArticle.getName(), paginated.getData().get(0).getName());
    assertEquals(dboArticle.getDescription(), paginated.getData().get(0).getDescription());
    assertEquals(dboArticle.getPrice(), paginated.getData().get(0).getPrice());
    assertEquals(dboArticle.getStock(), paginated.getData().get(0).getStock());
    assertEquals(dboArticle.getBrand().getId(), paginated.getData().get(0).getBrand().getId());
    assertEquals(dboArticle.getBrand().getName(), paginated.getData().get(0).getBrand().getName());
    assertEquals(dboArticle.getBrand().getDescription(), paginated.getData().get(0).getBrand().getDescription());
    assertEquals(dboArticle.getCategories().size(), paginated.getData().get(0).getCategories().size());
    assertEquals(1, paginated.getData().size());
  }


}
