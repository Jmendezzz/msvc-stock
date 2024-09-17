package com.emazon.msvc.stock.msvcstock.domain.ports.out.repositories;

import com.emazon.msvc.stock.msvcstock.domain.models.Article;
import com.emazon.msvc.stock.msvcstock.domain.models.Paginated;
import com.emazon.msvc.stock.msvcstock.domain.models.Pagination;
import com.emazon.msvc.stock.msvcstock.domain.models.Sorting;

import java.util.Optional;

public interface ArticleRepository {
  Article save(Article article);
  Paginated<Article> retrieveArticles(Pagination pagination, Sorting sorting);
  Optional<Article> findById(Long articleId);
  boolean existsById(Long articleId);
}
