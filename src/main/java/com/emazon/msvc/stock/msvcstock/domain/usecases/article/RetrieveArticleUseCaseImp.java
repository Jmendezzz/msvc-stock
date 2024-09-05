package com.emazon.msvc.stock.msvcstock.domain.usecases.article;

import com.emazon.msvc.stock.msvcstock.domain.factories.SortingValidationFactory;
import com.emazon.msvc.stock.msvcstock.domain.models.Article;
import com.emazon.msvc.stock.msvcstock.domain.models.Paginated;
import com.emazon.msvc.stock.msvcstock.domain.models.Pagination;
import com.emazon.msvc.stock.msvcstock.domain.models.Sorting;
import com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.article.RetrieveArticleUseCase;
import com.emazon.msvc.stock.msvcstock.domain.ports.out.repositories.ArticleRepository;
import com.emazon.msvc.stock.msvcstock.domain.validations.SortingValidation;

public class RetrieveArticleUseCaseImp implements RetrieveArticleUseCase {
  private final ArticleRepository articleRepository;
  private final SortingValidation sortingValidation;

  public RetrieveArticleUseCaseImp(ArticleRepository articleRepository) {
    this.articleRepository = articleRepository;
    this.sortingValidation = SortingValidationFactory.getSortingValidation(Article.class);
  }
  @Override
  public Paginated<Article> retrieveArticles(Pagination pagination, Sorting sorting){
    Sorting validatedSorting = sortingValidation.validateSorting(sorting);

    return articleRepository.retrieveArticles(pagination, validatedSorting);
  }
}
