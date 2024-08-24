package com.emazon.msvc.stock.msvcstock.domain.exceptions.article;

import com.emazon.msvc.stock.msvcstock.domain.exceptions.BusinessException;

public class ArticleBrandNotFoundException extends BusinessException {
  public ArticleBrandNotFoundException() {
    super(ArticleExceptionCode.BRAND_NOT_FOUND.getMessage(), ArticleExceptionCode.BRAND_NOT_FOUND.getCode());
  }
}
