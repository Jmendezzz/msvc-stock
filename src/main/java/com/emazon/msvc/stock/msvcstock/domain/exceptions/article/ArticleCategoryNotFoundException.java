package com.emazon.msvc.stock.msvcstock.domain.exceptions.article;

import com.emazon.msvc.stock.msvcstock.domain.exceptions.BusinessException;
import com.emazon.msvc.stock.msvcstock.domain.utils.constants.category.CategoryExceptionCode;
import com.emazon.msvc.stock.msvcstock.domain.utils.constants.category.CategoryExceptionMessage;

public class ArticleCategoryNotFoundException extends BusinessException {
  public ArticleCategoryNotFoundException() {
    super(CategoryExceptionMessage.CATEGORIES_NOT_FOUND, CategoryExceptionCode.CATEGORIES_NOT_FOUND);
  }
}
