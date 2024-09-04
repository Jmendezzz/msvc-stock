package com.emazon.msvc.stock.msvcstock.domain.exceptions.category;

import com.emazon.msvc.stock.msvcstock.domain.exceptions.BusinessException;
import com.emazon.msvc.stock.msvcstock.domain.utils.constants.category.CategoryExceptionCode;
import com.emazon.msvc.stock.msvcstock.domain.utils.constants.category.CategoryExceptionMessage;

public class DuplicateCategoryNameException extends BusinessException {
    public DuplicateCategoryNameException() {
        super(CategoryExceptionMessage.CATEGORY_NAME_ALREADY_EXISTS, CategoryExceptionCode.CATEGORY_NAME_ALREADY_EXISTS);
    }
}
