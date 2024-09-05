package com.emazon.msvc.stock.msvcstock.application.mappers;

import com.emazon.msvc.stock.msvcstock.application.dtos.article.ArticleResponseDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.article.CreateArticleRequestDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.article.ListArticleCategoryResponseDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.article.ListArticleResponseDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.category.CategoryResponseDto;
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
    CreateArticleRequestDto createArticleRequestDto = new CreateArticleRequestDto("articleName", "articleDescription", 200.0, 10, 1L, List.of(1L,2L));
    Article article = articleMapper.toDomain(createArticleRequestDto);

    assertEquals(createArticleRequestDto.name(), article.getName());
    assertEquals(createArticleRequestDto.description(), article.getDescription());
    assertEquals(createArticleRequestDto.price(), article.getPrice());
    assertEquals(createArticleRequestDto.stock(), article.getStock());
    assertEquals(createArticleRequestDto.brandId(), article.getBrand().getId());
    assertEquals(createArticleRequestDto.categoriesIds().size(), article.getCategories().size());
  }

  @Test
  void testCreateNullArticleDtoToArticle(){
    CreateArticleRequestDto createArticleRequestDto = null;
    assertNull(articleMapper.toDomain(createArticleRequestDto));
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

    ArticleResponseDto articleResponseDto = articleMapper.toDto(article);

    assertEquals(article.getId(), articleResponseDto.id());
    assertEquals(article.getName(), articleResponseDto.name());
    assertEquals(article.getDescription(), articleResponseDto.description());
    assertEquals(article.getPrice(), articleResponseDto.price());
    assertEquals(article.getStock(), articleResponseDto.stock());

    assertEquals(article.getBrand().getId(), articleResponseDto.brand().id());
    assertEquals(article.getBrand().getName(), articleResponseDto.brand().name());
    assertEquals(article.getBrand().getDescription(), articleResponseDto.brand().description());

    assertEquals(article.getCategories().stream().map(Category::getId).collect(Collectors.toSet()),
            articleResponseDto.categories().stream().map(CategoryResponseDto::id).collect(Collectors.toSet()));

    assertEquals(article.getCategories().stream().map(Category::getName).collect(Collectors.toSet()),
            articleResponseDto.categories().stream().map(CategoryResponseDto::name).collect(Collectors.toSet()));

    assertEquals(article.getCategories().stream().map(Category::getDescription).collect(Collectors.toSet()),
            articleResponseDto.categories().stream().map(CategoryResponseDto::description).collect(Collectors.toSet()));
  }


  @Test
  void testNullArticleToArticleDto(){
    Article article = null;
    assertNull(articleMapper.toDto(article));
  }

  @Test
  void articleListToListArticleDto(){
    List<Article> articles =  createArticleList();
    List<ListArticleResponseDto> articleDtos = articleMapper.toDto(articles);

    assertEquals(articles.size(), articleDtos.size());

    for (int i = 0; i < articles.size(); i++) {
      Article article = articles.get(i);
      ListArticleResponseDto articleDto = articleDtos.get(i);

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
        ListArticleCategoryResponseDto categoryDto = articleDto.categories().stream().toList().get(j);

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
    Paginated<ListArticleResponseDto> paginatedDto = articleMapper.toDtoPaginated(paginated);

    assertEquals(paginated.getCurrentPage(), paginatedDto.getCurrentPage());
    assertEquals(paginated.getTotalItems(), paginatedDto.getTotalItems());
    assertEquals(paginated.getTotalPages(), paginatedDto.getTotalPages());

    for (int i = 0; i < articles.size(); i++) {
      Article article = articles.get(i);
      ListArticleResponseDto articleDto = paginatedDto.getData().get(i);

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
        ListArticleCategoryResponseDto categoryDto = articleDto.categories().stream().toList().get(j);

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


