package com.emazon.msvc.stock.msvcstock.application.dtos.article;

import jakarta.validation.constraints.NotNull;

import static com.emazon.msvc.stock.msvcstock.domain.utils.constants.article.ArticleValidationMessage.ARTICLE_STOCK_UPDATE_STOCK_AMOUNT_REQUIRED;

public record DecreaseArticleStockRequestDto(
        @NotNull(message = ARTICLE_STOCK_UPDATE_STOCK_AMOUNT_REQUIRED)
        Integer quantity
) {
}
