package com.emazon.msvc.stock.msvcstock.domain.exceptions.article;

import com.emazon.msvc.stock.msvcstock.domain.exceptions.BusinessException;
import com.emazon.msvc.stock.msvcstock.domain.utils.constants.brand.BrandExceptionCode;
import com.emazon.msvc.stock.msvcstock.domain.utils.constants.brand.BrandExceptionMessage;

public class ArticleBrandNotFoundException extends BusinessException {
  public ArticleBrandNotFoundException() {
    super(BrandExceptionMessage.BRAND_NOT_FOUND, BrandExceptionCode.BRAND_NOT_FOUND);
  }
}
