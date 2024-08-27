package com.emazon.msvc.stock.msvcstock.application.services;

import com.emazon.msvc.stock.msvcstock.application.dtos.article.ArticleDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.article.CreateArticleDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.article.ListArticleDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.pagination.PaginationDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.sorting.SortingDto;
import com.emazon.msvc.stock.msvcstock.domain.models.Paginated;

import java.util.Set;

public interface ArticleService {
  ArticleDto createArticle(CreateArticleDto articleDto);
  Paginated<ListArticleDto> retrieveArticles(PaginationDto pagination, SortingDto sorting);
}
