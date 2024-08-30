package com.emazon.msvc.stock.msvcstock.domain.usecases.article;

import com.emazon.msvc.stock.msvcstock.domain.exceptions.InvalidInputException;
import com.emazon.msvc.stock.msvcstock.domain.exceptions.sorting.InvalidSortByFieldException;
import com.emazon.msvc.stock.msvcstock.domain.models.*;
import com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.article.RetrieveArticleUseCase;
import com.emazon.msvc.stock.msvcstock.domain.ports.out.repositories.ArticleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RetrieveArticleUseCaseTest {

  @Mock
  private ArticleRepository articleRepository;

  private RetrieveArticleUseCase retrieveArticleUseCaseImp;

  @BeforeEach
  void setUp() {
    retrieveArticleUseCaseImp = new RetrieveArticleUseCaseImp(articleRepository);
  }


  @Test
  void retrieveArticlesValidTest() {
    Pagination pagination = new Pagination(1, 10);
    Sorting sorting = new Sorting("name", "ASC");

    Category category = new Category(1L, "Category 1", "Desc 1");

    Article article1 = new Article(1L, "Article 1", "Description 1", 10.0, 100, new Brand(1L, "Brand 1", "Desc 1"), Set.of(category));
    Article article2 = new Article(2L, "Article 2", "Description 2", 15.0, 200, new Brand(2L, "Brand 2", "Desc 2"), Set.of(category));

    Paginated<Article> paginatedArticles = new Paginated<>(List.of(article1, article2), 1L, 2L, 1L);

    when(articleRepository.retrieveArticles(pagination, sorting)).thenReturn(paginatedArticles);

    Paginated<Article> result = retrieveArticleUseCaseImp.retrieveArticles(pagination, sorting);

    assertEquals(2, result.getData().size());
    verify(articleRepository, times(1)).retrieveArticles(pagination, sorting);
  }

  @Test
  void retrieveArticlesInvalidSortingThrowsInvalidSortByFieldExceptionTest() {
    Pagination pagination = new Pagination(1, 10);
    Sorting sorting = new Sorting("invalid_field", "ASC");

    assertThrows(InvalidSortByFieldException.class, () -> retrieveArticleUseCaseImp.retrieveArticles(pagination, sorting));
    verify(articleRepository, times(0)).retrieveArticles(any(Pagination.class), any(Sorting.class));
  }

  @Test
  void retrieveArticlesWithInvalidDirectionExceptionTest() {
    assertThrows(InvalidInputException.class, () -> new Sorting("name", "invalid_direction"));

    verify(articleRepository, times(0)).retrieveArticles(any(Pagination.class), any(Sorting.class));
  }

}
