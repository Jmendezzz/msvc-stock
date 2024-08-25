package com.emazon.msvc.stock.msvcstock.application.services.imp;

import com.emazon.msvc.stock.msvcstock.application.dtos.article.ArticleDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.article.CreateArticleDto;
import com.emazon.msvc.stock.msvcstock.application.services.ArticleService;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImp implements ArticleService {
  @Override
  public ArticleDto createArticle(CreateArticleDto articleDto) {
    return null;
  }
}
