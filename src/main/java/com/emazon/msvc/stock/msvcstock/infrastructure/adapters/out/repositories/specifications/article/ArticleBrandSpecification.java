package com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.repositories.specifications.article;

import com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.entities.ArticleEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import static com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.repositories.utils.constants.ArticleRepositoryConstant.BRAND_ID;
import static com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.repositories.utils.constants.ArticleRepositoryConstant.BRAND_NAME;

@AllArgsConstructor
public class ArticleBrandSpecification implements ArticleSpecification{
  private final Long brandId;

  @Override
  public Specification<ArticleEntity> getSpecification() {
    return (root, query, criteriaBuilder) -> {
      if(brandId == null) return criteriaBuilder.conjunction();
      return criteriaBuilder.equal(root.get(BRAND_NAME).get(BRAND_ID), brandId);
    };
  }
}
