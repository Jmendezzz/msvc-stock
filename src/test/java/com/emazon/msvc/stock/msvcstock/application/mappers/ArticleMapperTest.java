package com.emazon.msvc.stock.msvcstock.application.mappers;

import com.emazon.msvc.stock.msvcstock.application.dtos.article.ArticleDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.article.CreateArticleDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.article.ListArticleCategoryDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.article.ListArticleDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.category.CategoryDto;
import com.emazon.msvc.stock.msvcstock.domain.models.Article;
import com.emazon.msvc.stock.msvcstock.domain.models.Brand;
import com.emazon.msvc.stock.msvcstock.domain.models.Category;
import com.emazon.msvc.stock.msvcstock.domain.models.Paginated;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class ArticleMapperTest {

  private ArticleMapper articleMapper;

  @BeforeEach
  void setUp() {
    articleMapper = new ArticleMapperImpl(new BrandMapperImpl(), new CategoryMapperImpl());
  }
  @Test
  void testCreateArticleDtoToArticle(){
    CreateArticleDto createArticleDto = new CreateArticleDto("articleName", "articleDescription", 200.0, 10, 1L, Set.of(1L,2L));
    Article article = articleMapper.toDomain(createArticleDto);

    assertEquals(createArticleDto.name(), article.getName());
    assertEquals(createArticleDto.description(), article.getDescription());
    assertEquals(createArticleDto.price(), article.getPrice());
    assertEquals(createArticleDto.stock(), article.getStock());
    assertEquals(createArticleDto.brandId(), article.getBrand().getId());
    assertEquals(createArticleDto.categoriesIds(), article.getCategories().stream().map(Category::getId).collect(Collectors.toSet()));
  }

  @Test
  void testCreateNullArticleDtoToArticle(){
    CreateArticleDto createArticleDto = null;
    assertNull(articleMapper.toDomain(createArticleDto));
  }

  @Test
  void testArticleToArticleDto(){
    Article article = new Article(
            1L,
            "articleName",
            "articleDescription",
            200.0,
            10,
            new Brand(1L, "brandName", "brandDescription"),
            Set.of(new Category(1L, "categoryName", "categoryDescription"), new Category(2L, "categoryName", "categoryDescription")));

    ArticleDto articleDto = articleMapper.toDto(article);

    assertEquals(article.getId(), articleDto.id());
    assertEquals(article.getName(), articleDto.name());
    assertEquals(article.getDescription(), articleDto.description());
    assertEquals(article.getPrice(), articleDto.price());
    assertEquals(article.getStock(), articleDto.stock());

    assertEquals(article.getBrand().getId(), articleDto.brand().id());
    assertEquals(article.getBrand().getName(), articleDto.brand().name());
    assertEquals(article.getBrand().getDescription(), articleDto.brand().description());

    assertEquals(article.getCategories().stream().map(Category::getId).collect(Collectors.toSet()),
            articleDto.categories().stream().map(CategoryDto::id).collect(Collectors.toSet()));

    assertEquals(article.getCategories().stream().map(Category::getName).collect(Collectors.toSet()),
            articleDto.categories().stream().map(CategoryDto::name).collect(Collectors.toSet()));

    assertEquals(article.getCategories().stream().map(Category::getDescription).collect(Collectors.toSet()),
            articleDto.categories().stream().map(CategoryDto::description).collect(Collectors.toSet()));
  }


  @Test
  void testNullArticleToArticleDto(){
    Article article = null;
    assertNull(articleMapper.toDto(article));
  }

  @Test
  void articleListToListArticleDto(){
    List<Article> articles =  createArticleList();
    List<ListArticleDto> articleDtos = articleMapper.toDto(articles);

    assertEquals(articles.size(), articleDtos.size());

    for (int i = 0; i < articles.size(); i++) {
      Article article = articles.get(i);
      ListArticleDto articleDto = articleDtos.get(i);

      assertEquals(article.getId(), articleDto.id());
      assertEquals(article.getName(), articleDto.name());
      assertEquals(article.getDescription(), articleDto.description());
      assertEquals(article.getPrice(), articleDto.price());
      assertEquals(article.getStock(), articleDto.stock());

      assertEquals(article.getBrand().getId(), articleDto.brand().id());
      assertEquals(article.getBrand().getName(), articleDto.brand().name());
      assertEquals(article.getBrand().getDescription(), articleDto.brand().description());

      for(int j = 0; j < article.getCategories().size(); j++){
        Category category = article.getCategories().stream().toList().get(j);
        ListArticleCategoryDto categoryDto = articleDto.categories().stream().toList().get(j);

        assertEquals(category.getId(), categoryDto.id());
        assertEquals(category.getName(), categoryDto.name());
      }
    }

  }

  @Test
  void testNullArticleListToListArticleDto(){
    List<Article> articles = null;
    assertNull(articleMapper.toDto(articles));
  }

  @Test
  void articlePaginatedToArticleDtoPaginated(){
    List<Article> articles = createArticleList();
    Paginated<Article> paginated = new Paginated<>(articles, 0L, 2L, 1L);
    Paginated<ListArticleDto> paginatedDto = articleMapper.toDtoPaginated(paginated);

    assertEquals(paginated.getCurrentPage(), paginatedDto.getCurrentPage());
    assertEquals(paginated.getTotalItems(), paginatedDto.getTotalItems());
    assertEquals(paginated.getTotalPages(), paginatedDto.getTotalPages());

    for (int i = 0; i < articles.size(); i++) {
      Article article = articles.get(i);
      ListArticleDto articleDto = paginatedDto.getData().get(i);

      assertEquals(article.getId(), articleDto.id());
      assertEquals(article.getName(), articleDto.name());
      assertEquals(article.getDescription(), articleDto.description());
      assertEquals(article.getPrice(), articleDto.price());
      assertEquals(article.getStock(), articleDto.stock());

      assertEquals(article.getBrand().getId(), articleDto.brand().id());
      assertEquals(article.getBrand().getName(), articleDto.brand().name());
      assertEquals(article.getBrand().getDescription(), articleDto.brand().description());

      for(int j = 0; j < article.getCategories().size(); j++){
        Category category = article.getCategories().stream().toList().get(j);
        ListArticleCategoryDto categoryDto = articleDto.categories().stream().toList().get(j);

        assertEquals(category.getId(), categoryDto.id());
        assertEquals(category.getName(), categoryDto.name());
      }
    }

  }

  @Test
  void testNullArticlePaginatedToArticleDtoPaginated(){
    Paginated<Article> paginated = null;
    assertNull(articleMapper.toDtoPaginated(paginated));
  }

  private List<Article> createArticleList(){
    return List.of(
            new Article(
                    1L,
                    "articleName1",
                    "articleDescription1",
                    200.0,
                    10,
                    new Brand(1L, "brandName1", "brandDescription1"),
                    Set.of(new Category(1L, "categoryName1", "categoryDescription1"), new Category(2L, "categoryName1", "categoryDescription1"))),
            new Article(
                    2L,
                    "articleName2",
                    "articleDescription2",
                    300.0,
                    20,
                    new Brand(2L, "brandName2", "brandDescription2"),
                    Set.of(new Category(3L, "categoryName2", "categoryDescription2"), new Category(4L, "categoryName2", "categoryDescription2")))
    );
  }
}


