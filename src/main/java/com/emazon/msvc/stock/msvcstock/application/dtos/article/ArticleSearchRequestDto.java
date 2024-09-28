package com.emazon.msvc.stock.msvcstock.application.dtos.article;

import com.emazon.msvc.stock.msvcstock.application.dtos.pagination.PaginationDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.searchcriteria.ArticleSearchCriteriaRequestDto;
import com.emazon.msvc.stock.msvcstock.application.dtos.sorting.SortingDto;
import jakarta.validation.Valid;

public record ArticleSearchRequestDto(
        @Valid
        PaginationDto pagination,
        @Valid
        SortingDto sorting,
        ArticleSearchCriteriaRequestDto searchCriteria
) {
}
