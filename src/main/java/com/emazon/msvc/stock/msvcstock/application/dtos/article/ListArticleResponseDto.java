package com.emazon.msvc.stock.msvcstock.application.dtos.article;

import com.emazon.msvc.stock.msvcstock.application.dtos.brand.BrandResponseDto;

import java.util.Set;

public record ListArticleResponseDto(
        Long id,
        String name,
        String description,
        Double price,
        Integer stock,
        BrandResponseDto brand,
        Set<ListArticleCategoryResponseDto> categories
) {
}
