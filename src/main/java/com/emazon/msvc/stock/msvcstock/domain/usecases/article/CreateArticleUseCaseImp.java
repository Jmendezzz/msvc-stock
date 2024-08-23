package com.emazon.msvc.stock.msvcstock.domain.usecases.article;

import com.emazon.msvc.stock.msvcstock.domain.exceptions.article.InvalidCategoryArticleException;
import com.emazon.msvc.stock.msvcstock.domain.models.Article;
import com.emazon.msvc.stock.msvcstock.domain.models.Category;
import com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.CreateArticleUseCase;
import com.emazon.msvc.stock.msvcstock.domain.ports.out.repositories.ArticleRepository;
import com.emazon.msvc.stock.msvcstock.domain.ports.out.repositories.CategoryRepository;

import java.util.Set;
import java.util.stream.Collectors;

public class CreateArticleUseCaseImp implements CreateArticleUseCase {
  private final ArticleRepository articleRepository;
  private final CategoryRepository categoryRepository;
  public CreateArticleUseCaseImp(ArticleRepository articleRepository, CategoryRepository categoryRepository) {
    this.articleRepository = articleRepository;
    this.categoryRepository = categoryRepository;
  }
  @Override
  public Article create(Article article) {
    Set<Long> categoryIds = getCategoryIds(article);
    Set<Category> categories = categoryRepository.findAllById(categoryIds);

    if(categories.size() != categoryIds.size()) throw new InvalidCategoryArticleException();

    article.setCategories(categories);

    return articleRepository.save(article);
  }

  private Set<Long> getCategoryIds(Article article) {
    return article.getCategories()
            .stream()
            .map(Category::getId)
            .collect(Collectors.toSet());
  }

}
