package com.emazon.msvc.stock.msvcstock.application.dtos.article;

import com.emazon.msvc.stock.msvcstock.application.dtos.brand.BrandDto;

import java.util.Set;

public record ListArticleDto(
        Long id,
        String name,
        String description,
        Double price,
        Integer stock,
        BrandDto brand,
        Set<ListArticleCategoryDto> categories
) {
}
