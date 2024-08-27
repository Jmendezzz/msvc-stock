package com.emazon.msvc.stock.msvcstock.application.services.imp;

import com.emazon.msvc.stock.msvcstock.application.dtos.article.ArticleDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.article.CreateArticleDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.article.ListArticleDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.pagination.PaginationDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.sorting.SortingDto;
import com.emazon.msvc.stock.msvcstock.application.mappers.ArticleMapper;
import com.emazon.msvc.stock.msvcstock.application.mappers.PaginationMapper;
import com.emazon.msvc.stock.msvcstock.application.mappers.SortingMapper;
import com.emazon.msvc.stock.msvcstock.application.services.ArticleService;
import com.emazon.msvc.stock.msvcstock.domain.models.Article;
import com.emazon.msvc.stock.msvcstock.domain.models.Paginated;
import com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.article.CreateArticleUseCase;
import com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.article.RetrieveArticleUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ArticleServiceImp implements ArticleService {
  private final CreateArticleUseCase createArticleUseCase;
  private final RetrieveArticleUseCase retrieveArticleUseCase;
  private final ArticleMapper mapper;
  private final PaginationMapper paginationMapper;
  private final SortingMapper sortingMapper;

  @Override
  public ArticleDto createArticle(CreateArticleDto articleDto) {
    Article articleCreated = createArticleUseCase.create(mapper.toDomain(articleDto));
    return mapper.toDto(articleCreated);
  }

  @Override
  public Paginated<ListArticleDto> retrieveArticles(PaginationDto pagination, SortingDto sorting) {
    Paginated<Article> articles = retrieveArticleUseCase.retrieveArticles(
            paginationMapper.toDomain(pagination), sortingMapper.toDomain(sorting)
    );
    return mapper.toDtoPaginated(articles);

  }
}
