package com.emazon.msvc.stock.msvcstock.application.dtos.article;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;

import static com.emazon.msvc.stock.msvcstock.domain.utils.constants.article.ArticleValidationConstant.*;
import static com.emazon.msvc.stock.msvcstock.domain.utils.constants.article.ArticleValidationMessage.*;

public record CreateArticleRequestDto(
        @NotBlank(message = ARTICLE_NAME_REQUIRED)
        @Size(min = ARTICLE_NAME_MIN_LENGTH, max = ARTICLE_NAME_MAX_LENGTH, message = ARTICLE_NAME_INVALID_LENGTH)
        String name,
        @NotBlank(message = ARTICLE_DESCRIPTION_REQUIRED)
        @Size(min = ARTICLE_DESCRIPTION_MIN_LENGTH, max = ARTICLE_DESCRIPTION_MAX_LENGTH, message = ARTICLE_DESCRIPTION_INVALID_LENGTH)
        String description,
        @NotNull(message = ARTICLE_PRICE_REQUIRED)
        @DecimalMin(value = ARTICLE_MIN_PRICE_STRING, message = ARTICLE_PRICE_INVALID)
        Double price,
        @NotNull(message = ARTICLE_STOCK_REQUIRED)
        @Min(value = ARTICLE_MIN_STOCK, message = ARTICLE_STOCK_INVALID)
        Integer stock,

        @NotNull(message = ARTICLE_BRAND_REQUIRED)
        Long brandId,
        @NotNull(message = ARTICLE_CATEGORIES_REQUIRED)
        @Size(min = ARTICLE_MIN_CATEGORIES, max = ARTICLE_MAX_CATEGORIES, message = ARTICLE_CATEGORIES_INVALID_LENGTH)
        @UniqueElements(message = ARTICLE_CATEGORIES_REPEATED)
        List<Long> categoriesIds
) {
}
