package com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.repositories.specifications.article;

import com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.entities.ArticleEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import static com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.repositories.utils.constants.ArticleRepositoryConstant.CATEGORY_ID;
import static com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.repositories.utils.constants.ArticleRepositoryConstant.CATEGORY_NAME;

@AllArgsConstructor
public class ArticleCategorySpecification implements ArticleSpecification{
  private final Long categoryId;
  @Override
  public Specification<ArticleEntity> getSpecification() {
    return (root, query, criteriaBuilder) -> {
      if(categoryId == null) return criteriaBuilder.conjunction();
      return criteriaBuilder.equal(root.get(CATEGORY_NAME).get(CATEGORY_ID), categoryId);
    };
    }
}
