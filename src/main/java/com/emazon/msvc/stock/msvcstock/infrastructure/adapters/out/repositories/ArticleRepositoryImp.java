package com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.repositories;

import com.emazon.msvc.stock.msvcstock.domain.models.Article;
import com.emazon.msvc.stock.msvcstock.domain.ports.out.repositories.ArticleRepository;
import com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.entities.ArticleEntity;
import com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.mappers.DboArticleMapper;
import com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.repositories.jpa.JpaArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class ArticleRepositoryImp implements ArticleRepository {
  private final JpaArticleRepository jpaArticleRepository;
  private final DboArticleMapper mapper;
  @Override
  public Article save(Article article) {
    ArticleEntity articleEntity = jpaArticleRepository.save(mapper.toEntity(article));
    return mapper.toDomain(articleEntity);
  }
}
