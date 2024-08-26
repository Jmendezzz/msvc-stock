package com.emazon.msvc.stock.msvcstock.application.mappers;

import com.emazon.msvc.stock.msvcstock.application.dtos.article.ArticleDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.article.CreateArticleDto;
import com.emazon.msvc.stock.msvcstock.domain.models.Article;
import com.emazon.msvc.stock.msvcstock.domain.models.Brand;
import com.emazon.msvc.stock.msvcstock.domain.models.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {BrandMapper.class, CategoryMapper.class})
public interface ArticleMapper {

  @Mapping(source = "brandId", target = "brand")
  @Mapping(source = "categoriesIds", target = "categories")
  Article toDomain(CreateArticleDto createArticleDto);
  default Brand mapBrand(Long brandId) {
    Brand brand = new Brand();
    brand.setId(brandId);
    return brand;
  }

  default Set<Category> mapCategories(Set<Long> categoriesIds) {
    return categoriesIds.stream().map(id -> {
      Category category = new Category();
      category.setId(id);
      return category;
    }).collect(Collectors.toSet());
  }

  ArticleDto toDto(Article article);
}