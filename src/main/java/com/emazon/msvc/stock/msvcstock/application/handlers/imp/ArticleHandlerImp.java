package com.emazon.msvc.stock.msvcstock.application.handlers.imp;

import com.emazon.msvc.stock.msvcstock.application.dtos.article.ArticleResponseDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.article.CreateArticleRequestDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.article.ListArticleResponseDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.pagination.PaginationDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.sorting.SortingDto;
import com.emazon.msvc.stock.msvcstock.application.mappers.ArticleMapper;
import com.emazon.msvc.stock.msvcstock.application.mappers.PaginationMapper;
import com.emazon.msvc.stock.msvcstock.application.mappers.SortingMapper;
import com.emazon.msvc.stock.msvcstock.application.handlers.ArticleHandler;
import com.emazon.msvc.stock.msvcstock.domain.models.Article;
import com.emazon.msvc.stock.msvcstock.domain.models.Paginated;
import com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.article.CreateArticleUseCase;
import com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.article.RetrieveArticleUseCase;
import com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.article.UpdateArticleUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ArticleHandlerImp implements ArticleHandler {
  private final CreateArticleUseCase createArticleUseCase;
  private final RetrieveArticleUseCase retrieveArticleUseCase;
  private final UpdateArticleUseCase updateArticleUseCase;
  private final ArticleMapper mapper;
  private final PaginationMapper paginationMapper;
  private final SortingMapper sortingMapper;

  @Override
  public ArticleResponseDto createArticle(CreateArticleRequestDto articleDto) {
    Article articleCreated = createArticleUseCase.create(mapper.toDomain(articleDto));
    return mapper.toDto(articleCreated);
  }

  @Override
  public Paginated<ListArticleResponseDto> retrieveArticles(PaginationDto pagination, SortingDto sorting) {
    Paginated<Article> articles = retrieveArticleUseCase.retrieveArticles(
            paginationMapper.toDomain(pagination), sortingMapper.toDomain(sorting)
    );
    return mapper.toDtoPaginated(articles);

  }

  @Override
  public void updateArticleStock(Long articleId, Integer quantity) {
    updateArticleUseCase.updateArticleStock(articleId, quantity);
  }
}
