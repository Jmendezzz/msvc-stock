package com.emazon.msvc.stock.msvcstock.domain.exceptions.brand;

import com.emazon.msvc.stock.msvcstock.domain.exceptions.BusinessException;

public class DuplicateBrandNameException extends BusinessException {
  public DuplicateBrandNameException() {
    super(BrandExceptionCode.BRAND_ALREADY_EXISTS.getCode(), BrandExceptionCode.BRAND_ALREADY_EXISTS.getMessage());
  }
}
