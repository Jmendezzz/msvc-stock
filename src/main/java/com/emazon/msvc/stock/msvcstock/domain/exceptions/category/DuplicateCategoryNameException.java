package com.emazon.msvc.stock.msvcstock.domain.exceptions.category;

import com.emazon.msvc.stock.msvcstock.domain.exceptions.BusinessException;

public class DuplicateCategoryNameException extends BusinessException {
    public DuplicateCategoryNameException() {
        super(CategoryErrorCode.DUPLICATE_NAME.getCode(), CategoryErrorCode.DUPLICATE_NAME.getMessage());
    }
}
