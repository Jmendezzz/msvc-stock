package com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.repositories.specifications.article;

import com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.entities.ArticleEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ArticleSpecificationBuilder {
  private final List<Specification<ArticleEntity>> specifications = new ArrayList<>();
  public ArticleSpecificationBuilder withBrand(Long brandId) {
    specifications.add(new ArticleBrandSpecification(brandId).getSpecification());
    return this;
  }

  public ArticleSpecificationBuilder withCategory(Long categoryId) {
    specifications.add(new ArticleCategorySpecification(categoryId).getSpecification());
    return this;
  }
  public ArticleSpecificationBuilder withArticleIds(List<Long> articleIds) {
    specifications.add(new ArticleIdsSpecification(articleIds).getSpecification());
    return this;
  }

  public Specification<ArticleEntity> build() {
    return specifications.stream()
            .reduce(Specification::and)
            .orElse(null);
  }
}
