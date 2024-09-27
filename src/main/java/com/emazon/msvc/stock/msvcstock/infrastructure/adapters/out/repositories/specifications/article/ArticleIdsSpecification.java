package com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.repositories.specifications.article;

import com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.entities.ArticleEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

import static com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.repositories.utils.constants.ArticleRepositoryConstant.ARTICLE_ID;

@AllArgsConstructor
public class ArticleIdsSpecification implements ArticleSpecification{
  private final List<Long> articleIds;
  @Override
  public Specification<ArticleEntity> getSpecification() {
    return (root, query, criteriaBuilder) -> {
      if (articleIds == null || articleIds.isEmpty()) return criteriaBuilder.conjunction();
      return root.get(ARTICLE_ID).in(articleIds);
    };
  }
}
