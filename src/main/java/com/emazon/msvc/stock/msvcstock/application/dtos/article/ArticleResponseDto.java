package com.emazon.msvc.stock.msvcstock.application.dtos.article;

import com.emazon.msvc.stock.msvcstock.application.dtos.brand.BrandResponseDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.category.CategoryResponseDto;

import java.util.Set;

public record ArticleResponseDto(
        Long id,
        String name,
        String description,
        Double price,
        Integer stock,
        BrandResponseDto brand,
        Set<CategoryResponseDto> categories


) {
}
