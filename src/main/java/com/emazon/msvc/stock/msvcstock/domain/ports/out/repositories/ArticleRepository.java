package com.emazon.msvc.stock.msvcstock.domain.ports.out.repositories;

import com.emazon.msvc.stock.msvcstock.domain.models.*;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {
  Article save(Article article);
  Paginated<Article> retrieveArticles(Pagination pagination, Sorting sorting, ArticleSearchCriteria articleSearchCriteria);
  Optional<Article> findById(Long articleId);
  boolean existsById(Long articleId);
  List<Article> findAllByIds(List<Long> articleIds);
}
