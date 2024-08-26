package com.emazon.msvc.stock.msvcstock.application.services.imp;

import com.emazon.msvc.stock.msvcstock.application.dtos.article.ArticleDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.article.CreateArticleDto;
import com.emazon.msvc.stock.msvcstock.application.mappers.ArticleMapper;
import com.emazon.msvc.stock.msvcstock.application.services.ArticleService;
import com.emazon.msvc.stock.msvcstock.domain.models.Article;
import com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.article.CreateArticleUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ArticleServiceImp implements ArticleService {

  private final CreateArticleUseCase createArticleUseCase;
  private final ArticleMapper mapper;

  @Override
  public ArticleDto createArticle(CreateArticleDto articleDto) {
    Article articleCreated = createArticleUseCase.create(mapper.toDomain(articleDto));
    return mapper.toDto(articleCreated);
  }
}
