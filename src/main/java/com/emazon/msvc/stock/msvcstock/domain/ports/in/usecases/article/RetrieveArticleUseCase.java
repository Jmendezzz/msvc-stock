package com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.article;

import com.emazon.msvc.stock.msvcstock.domain.models.*;

import java.util.List;
import java.util.Optional;


public interface RetrieveArticleUseCase {
  Paginated<Article> retrieveArticles(Pagination pagination, Sorting sorting, ArticleSearchCriteria searchCriteria);
  boolean articleExists(Long articleId);
  Optional<Article> retrieveArticleById(Long articleId);
  List<Article> retrieveArticlesByIds(List<Long> articleIds);
}
