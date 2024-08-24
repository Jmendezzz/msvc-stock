package com.emazon.msvc.stock.msvcstock.application.dtos.article;

import com.emazon.msvc.stock.msvcstock.application.dtos.brand.BrandDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.category.CategoryDto;

import java.util.Set;

public record ArticleDto(
        Long id,
        String name,
        String description,
        Double price,
        Integer stock,
        BrandDto brand,
        Set<CategoryDto> categories


) {
}
