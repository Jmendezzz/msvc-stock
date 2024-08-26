package com.emazon.msvc.stock.msvcstock.domain.usecases.article;

import com.emazon.msvc.stock.msvcstock.domain.exceptions.article.ArticleBrandNotFoundException;
import com.emazon.msvc.stock.msvcstock.domain.exceptions.article.ArticleCategoryNotFoundException;
import com.emazon.msvc.stock.msvcstock.domain.models.Article;
import com.emazon.msvc.stock.msvcstock.domain.models.Brand;
import com.emazon.msvc.stock.msvcstock.domain.models.Category;
import com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.article.CreateArticleUseCase;
import com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.brand.RetrieveBrandUseCase;
import com.emazon.msvc.stock.msvcstock.domain.ports.in.usecases.category.RetrieveCategoryUseCase;
import com.emazon.msvc.stock.msvcstock.domain.ports.out.repositories.ArticleRepository;

import java.util.Set;
import java.util.stream.Collectors;

public class CreateArticleUseCaseImp implements CreateArticleUseCase {
  private final ArticleRepository articleRepository;
  private final RetrieveCategoryUseCase retrieveCategoryUseCase;
  private final RetrieveBrandUseCase retrieveBrandUseCase;
  public CreateArticleUseCaseImp(ArticleRepository articleRepository, RetrieveCategoryUseCase retrieveCategoryUseCase, RetrieveBrandUseCase retrieveBrandUseCase) {
    this.articleRepository = articleRepository;
    this.retrieveCategoryUseCase = retrieveCategoryUseCase;
    this.retrieveBrandUseCase = retrieveBrandUseCase;
  }
  @Override
  public Article create(Article article) {
    Set<Category> categories = validateExistingCategories(article.getCategories());
    Brand brand = validateExistingBrand(article.getBrand());

    article.setBrand(brand);
    article.setCategories(categories);

    return articleRepository.save(article);
  }

  private Set<Category> validateExistingCategories(Set<Category> categories) {
    Set<Long> categoryIds = categories.stream().map(Category::getId).collect(Collectors.toSet());
    Set<Category> existingCategories = retrieveCategoryUseCase.retrieveCategoriesByIds(categoryIds);

    if(existingCategories.size() != categoryIds.size()) throw new ArticleCategoryNotFoundException();

    return existingCategories;
  }

  private Brand validateExistingBrand(Brand brand) {
    return retrieveBrandUseCase
            .retrieveBrandById(brand.getId())
            .orElseThrow(ArticleBrandNotFoundException::new);
  }
}
