package com.emazon.msvc.stock.msvcstock.application.handlers;

import com.emazon.msvc.stock.msvcstock.application.dtos.article.ArticleResponseDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.article.CreateArticleRequestDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.article.ListArticleResponseDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.pagination.PaginationDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.searchcriteria.ArticleSearchCriteriaRequestDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.sorting.SortingDto;
import com.emazon.msvc.stock.msvcstock.domain.models.Paginated;

import java.util.List;
import java.util.Optional;

public interface ArticleHandler {
  ArticleResponseDto createArticle(CreateArticleRequestDto articleDto);
  Paginated<ListArticleResponseDto> retrieveArticles(PaginationDto pagination, SortingDto sorting, ArticleSearchCriteriaRequestDto searchCriteria);
  void updateArticleStock(Long articleId, Integer quantity);
  void decreaseArticleStock(Long articleId, Integer quantity);
  boolean articleExists(Long articleId);
  Optional<ArticleResponseDto> retrieveArticleById(Long articleId);
  List<ArticleResponseDto> retrieveArticlesByIds(List<Long> articleIds);
}
