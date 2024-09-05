package com.emazon.msvc.stock.msvcstock.domain.exceptions.brand;

import com.emazon.msvc.stock.msvcstock.domain.exceptions.BusinessException;
import com.emazon.msvc.stock.msvcstock.domain.utils.constants.brand.BrandExceptionCode;
import com.emazon.msvc.stock.msvcstock.domain.utils.constants.brand.BrandExceptionMessage;

public class DuplicateBrandNameException extends BusinessException {
  public DuplicateBrandNameException() {
    super(BrandExceptionMessage.BRAND_NAME_ALREADY_EXISTS, BrandExceptionCode.BRAND_NAME_ALREADY_EXISTS);
  }
}
