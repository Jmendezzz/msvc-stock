package com.emazon.msvc.stock.msvcstock.domain.exceptions.article;

import com.emazon.msvc.stock.msvcstock.domain.exceptions.BusinessException;

import static com.emazon.msvc.stock.msvcstock.domain.utils.constants.article.ArticleExceptionCode.INVALID_DECREASE_STOCK_QUANTITY_CODE;
import static com.emazon.msvc.stock.msvcstock.domain.utils.constants.article.ArticleExceptionMessage.INVALID_DECREASE_STOCK_QUANTITY;

public class InvalidDecreaseStockQuantity extends BusinessException {
  public InvalidDecreaseStockQuantity() {
    super(INVALID_DECREASE_STOCK_QUANTITY, INVALID_DECREASE_STOCK_QUANTITY_CODE
    );
  }
}
