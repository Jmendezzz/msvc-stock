package com.emazon.msvc.stock.msvcstock.application.handlers;

import com.emazon.msvc.stock.msvcstock.application.dtos.article.ArticleResponseDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.article.CreateArticleRequestDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.article.ListArticleCategoryResponseDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.article.ListArticleResponseDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.brand.BrandResponseDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.category.CategoryResponseDto;
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
    CreateArticleRequestDto createArticleRequestDto = new CreateArticleRequestDto("articleName", "articleDescription", 100.0, 10, 1L, List.of(1L,2L));
    Article expectedArticle = new Article(1L, "articleName", "articleDescription", 100.0, 10,
            new Brand(1L,"BrandName","BrandDesc"),
            createCategories());

    ArticleResponseDto expectedResult = new ArticleResponseDto(1L, "articleName", "articleDescription", 100.0, 10, new BrandResponseDto(1L, "BrandName","BrandDesc"), createCategoriesDto());

    when(createArticleUseCase.create(mapper.toDomain(createArticleRequestDto))).thenReturn(expectedArticle);
    when(mapper.toDto(expectedArticle)).thenReturn(expectedResult);

    ArticleResponseDto createdArticle = articleHandler.createArticle(createArticleRequestDto);

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
    Paginated<ListArticleResponseDto> expectedPaginatedArticleDto = new Paginated<>(List.of(
            new ListArticleResponseDto(1L, "First", "articleDescription1", 100.0, 10, new BrandResponseDto(1L, "BrandName","BrandDesc"), createListArticleCategoryDto()),
            new ListArticleResponseDto(2L, "Second", "articleDescription2", 200.0, 20, new BrandResponseDto(2L, "BrandName2","BrandDesc2"), createListArticleCategoryDto())
    ), 0L,0L,0L);

    when(paginationMapper.toDomain(paginationDto)).thenReturn(pagination);
    when(sortingMapper.toDomain(sortingDto)).thenReturn(sorting);
    when(retrieveArticleUseCase.retrieveArticles(pagination, sorting)).thenReturn(expectedResult);
    when(mapper.toDtoPaginated(expectedResult)).thenReturn(expectedPaginatedArticleDto);

    Paginated<ListArticleResponseDto> result = articleHandler.retrieveArticles(paginationDto, sortingDto);

    assertEquals(expectedPaginatedArticleDto, result);
  }
  private List<Category> createCategories() {
    return List.of(
            new Category(1L, "category1", "categoryDescription"),
            new Category(2L, "category2", "categoryDescription")
    );
  }

  private Set<CategoryResponseDto> createCategoriesDto() {
    Set<CategoryResponseDto> categories = new HashSet<>();
    categories.add(new CategoryResponseDto(1L, "category1", "categoryDescription"));
    categories.add(new CategoryResponseDto(2L, "category2", "categoryDescription"));
    return categories;
  }
  private Set<ListArticleCategoryResponseDto> createListArticleCategoryDto() {
    Set<ListArticleCategoryResponseDto> categories = new HashSet<>();
    categories.add(new ListArticleCategoryResponseDto(1L, "category1"));
    categories.add(new ListArticleCategoryResponseDto(2L, "category2"));
    return categories;
  }
}
