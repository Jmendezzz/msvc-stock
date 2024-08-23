package com.emazon.msvc.stock.msvcstock.domain.exceptions.article;

import com.emazon.msvc.stock.msvcstock.domain.exceptions.BusinessException;

public class InvalidCategoryArticleException extends BusinessException {
  public InvalidCategoryArticleException() {
    super(ArticleExceptionCode.INVALID_CATEGORIES.getMessage(), ArticleExceptionCode.INVALID_CATEGORIES.getCode());
  }
}
