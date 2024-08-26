package com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.mappers;

import com.emazon.msvc.stock.msvcstock.domain.models.Article;
import com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.entities.ArticleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {DboBrandMapper.class, DboCategoryMapper.class})
public interface DboArticleMapper {

  Article toDomain(ArticleEntity articleEntity);
  ArticleEntity toEntity(Article article);
}
