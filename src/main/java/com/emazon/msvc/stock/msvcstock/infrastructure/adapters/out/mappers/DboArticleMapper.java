package com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.mappers;

import com.emazon.msvc.stock.msvcstock.domain.models.Article;
import com.emazon.msvc.stock.msvcstock.domain.models.Paginated;
import com.emazon.msvc.stock.msvcstock.infrastructure.adapters.out.entities.ArticleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring", uses = {DboBrandMapper.class, DboCategoryMapper.class})
public interface DboArticleMapper {

  Article toDomain(ArticleEntity articleEntity);
  List<Article> toDomain(List<ArticleEntity> articleEntities);
  ArticleEntity toEntity(Article article);
  @Mapping(target = "data", expression = "java(toDomain((page.getContent())))")
  @Mapping(target = "currentPage", expression = "java((long) page.getNumber())")
  @Mapping(target = "totalItems", expression = "java(page.getTotalElements())")
  @Mapping(target = "totalPages", expression = "java((long) page.getTotalPages())")
  Paginated<Article> toDomainPaginated(Page<ArticleEntity> page);
}
