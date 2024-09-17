package com.emazon.msvc.stock.msvcstock.domain.exceptions.article;

import com.emazon.msvc.stock.msvcstock.domain.exceptions.BusinessException;

import static com.emazon.msvc.stock.msvcstock.domain.utils.constants.article.ArticleExceptionCode.ARTICLE_NOT_FOUND_CODE;
import static com.emazon.msvc.stock.msvcstock.domain.utils.constants.article.ArticleExceptionMessage.ARTICLE_NOT_FOUND;

public class ArticleNotFoundException extends BusinessException {
  public ArticleNotFoundException() {
    super(ARTICLE_NOT_FOUND, ARTICLE_NOT_FOUND_CODE);
  }
}
