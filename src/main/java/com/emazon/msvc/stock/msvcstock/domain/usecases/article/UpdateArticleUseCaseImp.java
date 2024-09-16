package com.emazon.msvc.stock.msvcstock.domain.usecases.article;

import com.emazon.msvc.stock.msvcstock.domain.exceptions.article.ArticleNotFoundException;
import com.emazon.msvc.stock.msvcstock.domain.models.Article;
import com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.article.UpdateArticleUseCase;
import com.emazon.msvc.stock.msvcstock.domain.ports.out.repositories.ArticleRepository;

import java.util.Optional;

public class UpdateArticleUseCaseImp implements UpdateArticleUseCase {
  private final ArticleRepository articleRepository;

  public UpdateArticleUseCaseImp(ArticleRepository articleRepository) {
    this.articleRepository = articleRepository;
  }

  @Override
  public void updateArticleStock(Long articleId, Integer quantity) {
    Optional<Article> article = articleRepository.findById(articleId);
    if(article.isEmpty()) {
      throw new ArticleNotFoundException();
    }

    Article articleToUpdate = article.get();

    articleToUpdate.setStock(articleToUpdate.getStock() + quantity);

    articleRepository.save(articleToUpdate);
  }
}
