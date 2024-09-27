package com.emazon.msvc.stock.msvcstock.domain.usecases.article;

import com.emazon.msvc.stock.msvcstock.domain.factories.SortingValidationFactory;
import com.emazon.msvc.stock.msvcstock.domain.models.*;
import com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.article.RetrieveArticleUseCase;
import com.emazon.msvc.stock.msvcstock.domain.ports.out.repositories.ArticleRepository;
import com.emazon.msvc.stock.msvcstock.domain.validations.SortingValidation;

import java.util.List;
import java.util.Optional;

public class RetrieveArticleUseCaseImp implements RetrieveArticleUseCase {
  private final ArticleRepository articleRepository;
  private final SortingValidation sortingValidation;

  public RetrieveArticleUseCaseImp(ArticleRepository articleRepository) {
    this.articleRepository = articleRepository;
    this.sortingValidation = SortingValidationFactory.getSortingValidation(Article.class);
  }
  @Override
  public Paginated<Article> retrieveArticles(Pagination pagination, Sorting sorting, ArticleSearchCriteria searchCriteria){
    Sorting validatedSorting = sortingValidation.validateSorting(sorting);

    return articleRepository.retrieveArticles(pagination, validatedSorting, searchCriteria);
  }

  @Override
  public boolean articleExists(Long articleId) {
    return articleRepository.existsById(articleId);
  }

  @Override
  public Optional<Article> retrieveArticleById(Long articleId) {
    return articleRepository.findById(articleId);
  }

  @Override
  public List<Article> retrieveArticlesByIds(List<Long> articleIds) {
    return articleRepository.findAllByIds(articleIds);
  }
}
