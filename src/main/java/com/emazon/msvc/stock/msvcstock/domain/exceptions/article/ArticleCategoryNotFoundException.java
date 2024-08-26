package com.emazon.msvc.stock.msvcstock.domain.exceptions.article;

import com.emazon.msvc.stock.msvcstock.domain.exceptions.BusinessException;

public class ArticleCategoryNotFoundException extends BusinessException {
  public ArticleCategoryNotFoundException() {
    super(ArticleExceptionCode.CATEGORIES_NOT_FOUND.getMessage(), ArticleExceptionCode.CATEGORIES_NOT_FOUND.getCode());
  }
}
