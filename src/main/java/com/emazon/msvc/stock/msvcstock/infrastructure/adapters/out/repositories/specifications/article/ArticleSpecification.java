package com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.repositories.specifications.article;

import com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.entities.ArticleEntity;
import org.springframework.data.jpa.domain.Specification;

public interface ArticleSpecification {
  Specification<ArticleEntity> getSpecification();
}
