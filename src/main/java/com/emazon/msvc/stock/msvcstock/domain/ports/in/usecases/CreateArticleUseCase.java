package com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases;

import com.emazon.msvc.stock.msvcstock.domain.models.Article;

public interface CreateArticleUseCase {
  Article create(Article article);
}
