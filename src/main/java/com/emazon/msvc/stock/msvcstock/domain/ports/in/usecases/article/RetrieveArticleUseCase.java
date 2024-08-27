package com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.article;

import com.emazon.msvc.stock.msvcstock.domain.models.Article;
import com.emazon.msvc.stock.msvcstock.domain.models.Paginated;
import com.emazon.msvc.stock.msvcstock.domain.models.Pagination;
import com.emazon.msvc.stock.msvcstock.domain.models.Sorting;


public interface RetrieveArticleUseCase {
  Paginated<Article> retrieveArticles(Pagination pagination, Sorting sorting);
}
