package com.emazon.msvc.stock.msvcstock.domain.exceptions.category;

import com.emazon.msvc.stock.msvcstock.domain.exceptions.BusinessException;

public class DuplicateCategoryNameException extends BusinessException {
    public DuplicateCategoryNameException() {
        super(CategoryExceptionCode.DUPLICATE_NAME.getCode(), CategoryExceptionCode.DUPLICATE_NAME.getMessage());
    }
}
