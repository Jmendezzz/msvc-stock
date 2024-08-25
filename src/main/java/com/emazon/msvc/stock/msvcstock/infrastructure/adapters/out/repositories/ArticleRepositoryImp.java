package com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.repositories;

import com.emazon.msvc.stock.msvcstock.domain.models.Article;
import com.emazon.msvc.stock.msvcstock.domain.ports.out.repositories.ArticleRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleRepositoryImp implements ArticleRepository {
  @Override
  public Article save(Article article) {
    return null;
  }
}
