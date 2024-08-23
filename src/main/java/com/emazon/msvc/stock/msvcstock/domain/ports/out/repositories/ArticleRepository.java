package com.emazon.msvc.stock.msvcstock.domain.ports.out.repositories;

import com.emazon.msvc.stock.msvcstock.domain.models.Article;

public interface ArticleRepository {
  Article save(Article article);
}
