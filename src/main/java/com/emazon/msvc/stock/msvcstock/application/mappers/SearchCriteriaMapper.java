package com.emazon.msvc.stock.msvcstock.application.mappers;

import com.emazon.msvc.stock.msvcstock.application.dtos.searchcriteria.ArticleSearchCriteriaRequestDto;
import com.emazon.msvc.stock.msvcstock.domain.models.ArticleSearchCriteria;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SearchCriteriaMapper {
  ArticleSearchCriteria toDomain(ArticleSearchCriteriaRequestDto searchCriteriaRequestDto);
}
