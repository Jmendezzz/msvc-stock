package com.emazon.msvc.stock.msvcstock.application.mappers;

import com.emazon.msvc.stock.msvcstock.application.dtos.article.ArticleResponseDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.article.CreateArticleRequestDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.article.ListArticleResponseDto;
import com.emazon.msvc.stock.msvcstock.domain.models.Article;
import com.emazon.msvc.stock.msvcstock.domain.models.Brand;
import com.emazon.msvc.stock.msvcstock.domain.models.Category;
import com.emazon.msvc.stock.msvcstock.domain.models.Paginated;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        uses = {BrandMapper.class, CategoryMapper.class},
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ArticleMapper {

  @Mapping(source = "brandId", target = "brand")
  @Mapping(source = "categoriesIds", target = "categories")
  Article toDomain(CreateArticleRequestDto createArticleRequestDto);
  default Brand mapBrand(Long brandId) {
    Brand brand = new Brand();
    brand.setId(brandId);
    return brand;
  }

  default Set<Category> mapCategories(List<Long> categoriesIds) {
    return categoriesIds.stream().map(id -> {
      Category category = new Category();
      category.setId(id);
      return category;
    }).collect(Collectors.toSet());
  }

  ArticleResponseDto toDto(Article article);
  List<ListArticleResponseDto> toDto(List<Article> articles);
  @Mapping(target = "data", expression = "java(toDto(paginated.getData()))")
  @Mapping(target = "currentPage", expression = "java(paginated.getCurrentPage())")
  @Mapping(target = "totalItems", expression = "java(paginated.getTotalItems())")
  @Mapping(target = "totalPages", expression = "java(paginated.getTotalPages())")
  Paginated<ListArticleResponseDto> toDtoPaginated(Paginated<Article> paginated);
}