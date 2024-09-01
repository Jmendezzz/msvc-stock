package com.emazon.msvc.stock.msvcstock.application.handlers;

import com.emazon.msvc.stock.msvcstock.application.dtos.article.ArticleDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.article.CreateArticleDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.article.ListArticleCategoryDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.article.ListArticleDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.brand.BrandDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.category.CategoryDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.pagination.PaginationDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.sorting.SortingDto;
import com.emazon.msvc.stock.msvcstock.application.mappers.ArticleMapper;
import com.emazon.msvc.stock.msvcstock.application.mappers.PaginationMapper;
import com.emazon.msvc.stock.msvcstock.application.mappers.SortingMapper;
import com.emazon.msvc.stock.msvcstock.application.handlers.imp.ArticleHandlerImp;
import com.emazon.msvc.stock.msvcstock.domain.models.*;
import com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.article.CreateArticleUseCase;
import com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.article.RetrieveArticleUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ArticleHandlerTest {

  private ArticleHandler articleHandler;
  @Mock
  private CreateArticleUseCase createArticleUseCase;
  @Mock
  private RetrieveArticleUseCase retrieveArticleUseCase;
  @Mock
  private ArticleMapper mapper;
  @Mock
  private PaginationMapper paginationMapper;
  @Mock
  private SortingMapper sortingMapper;


  @BeforeEach
  public void setUp() {
    articleHandler = new ArticleHandlerImp(
      createArticleUseCase,
      retrieveArticleUseCase,
      mapper,
      paginationMapper,
      sortingMapper
    );
  }

  @Test
  void createArticleTest() {
    CreateArticleDto createArticleDto = new CreateArticleDto("articleName", "articleDescription", 100.0, 10, 1L, Set.of(1L,2L));
    Article expectedArticle = new Article(1L, "articleName", "articleDescription", 100.0, 10,
            new Brand(1L,"BrandName","BrandDesc"),
            createCategories());

    ArticleDto expectedResult = new ArticleDto(1L, "articleName", "articleDescription", 100.0, 10, new BrandDto(1L, "BrandName","BrandDesc"), createCategoriesDto());

    when(createArticleUseCase.create(mapper.toDomain(createArticleDto))).thenReturn(expectedArticle);
    when(mapper.toDto(expectedArticle)).thenReturn(expectedResult);

    ArticleDto createdArticle = articleHandler.createArticle(createArticleDto);

    assertEquals(expectedResult, createdArticle);
  }
  @Test
  void retrieveArticlesTest(){
    PaginationDto paginationDto = new PaginationDto(0, 10);
    SortingDto sortingDto = new SortingDto("name", "ASC");

    // Prepare input mapped to domain
    Pagination pagination = new Pagination(0, 10);
    Sorting sorting = new Sorting("name", "ASC");

    // Use case expected result
    Paginated<Article> expectedResult = new Paginated<>(List.of(
            new Article(1L, "First", "articleDescription1", 100.0, 10, new Brand(1L,"BrandName","BrandDesc"), createCategories()),
            new Article(2L, "Second", "articleDescription2", 200.0, 20, new Brand(2L,"BrandName2","BrandDesc2"), createCategories())
    ), 0L,0L,0L);

    // Prepare expected result
    Paginated<ListArticleDto> expectedPaginatedArticleDto = new Paginated<>(List.of(
            new ListArticleDto(1L, "First", "articleDescription1", 100.0, 10, new BrandDto(1L, "BrandName","BrandDesc"), createListArticleCategoryDto()),
            new ListArticleDto(2L, "Second", "articleDescription2", 200.0, 20, new BrandDto(2L, "BrandName2","BrandDesc2"), createListArticleCategoryDto())
    ), 0L,0L,0L);

    when(paginationMapper.toDomain(paginationDto)).thenReturn(pagination);
    when(sortingMapper.toDomain(sortingDto)).thenReturn(sorting);
    when(retrieveArticleUseCase.retrieveArticles(pagination, sorting)).thenReturn(expectedResult);
    when(mapper.toDtoPaginated(expectedResult)).thenReturn(expectedPaginatedArticleDto);

    Paginated<ListArticleDto> result = articleHandler.retrieveArticles(paginationDto, sortingDto);

    assertEquals(expectedPaginatedArticleDto, result);
  }
  private Set<Category> createCategories() {
    Set<Category> categories = new HashSet<>();
    categories.add(new Category(1L, "category1","categoryDescription"));
    categories.add(new Category(2L, "category2","categoryDescription"));
    return categories;
  }

  private Set<CategoryDto> createCategoriesDto() {
    Set<CategoryDto> categories = new HashSet<>();
    categories.add(new CategoryDto(1L, "category1", "categoryDescription"));
    categories.add(new CategoryDto(2L, "category2", "categoryDescription"));
    return categories;
  }
  private Set<ListArticleCategoryDto> createListArticleCategoryDto() {
    Set<ListArticleCategoryDto> categories = new HashSet<>();
    categories.add(new ListArticleCategoryDto(1L, "category1"));
    categories.add(new ListArticleCategoryDto(2L, "category2"));
    return categories;
  }
}
