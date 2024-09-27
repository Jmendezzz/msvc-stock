package com.emazon.msvc.stock.msvcstock.application.dtos.searchcriteria;

import java.util.List;

public record ArticleSearchCriteriaRequestDto(
        List<Long> articleIds,
        String articleName,
        Long categoryId,
        Long brandId
) {
}
