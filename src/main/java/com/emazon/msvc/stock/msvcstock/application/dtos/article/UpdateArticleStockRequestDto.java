package com.emazon.msvc.stock.msvcstock.application.dtos.article;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import static com.emazon.msvc.stock.msvcstock.domain.utils.constants.article.ArticleValidationConstant.ARTICLE_MIN_STOCK_UPDATE;
import static com.emazon.msvc.stock.msvcstock.domain.utils.constants.article.ArticleValidationMessage.ARTICLE_STOCK_UPDATE_STOCK_AMOUNT_INVALID;
import static com.emazon.msvc.stock.msvcstock.domain.utils.constants.article.ArticleValidationMessage.ARTICLE_STOCK_UPDATE_STOCK_AMOUNT_REQUIRED;

public record UpdateArticleStockRequestDto(
        @NotNull(message = ARTICLE_STOCK_UPDATE_STOCK_AMOUNT_REQUIRED)
        @Min(value = ARTICLE_MIN_STOCK_UPDATE, message = ARTICLE_STOCK_UPDATE_STOCK_AMOUNT_INVALID)
        Integer quantity
) {
}
