package com.emazon.msvc.stock.msvcstock.application.dtos.article;

import java.util.Set;

public record CreateArticleDto(
        String name,
        String description,
        Double price,
        Integer stock,
        Long brandId,
        Set<Long> categoriesIds
) {
}
