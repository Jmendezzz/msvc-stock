package com.emazon.msvc.stock.msvcstock.application.dtos.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import static com.emazon.msvc.stock.msvcstock.domain.utils.constants.category.CategoryValidationMessage.*;
import static com.emazon.msvc.stock.msvcstock.domain.utils.constants.category.CategoryValidationConstant.*;
public record CreateCategoryRequestDto(
    @NotBlank(message = CATEGORY_NAME_REQUIRED)
    @Size(min = CATEGORY_NAME_MIN_LENGTH, max = CATEGORY_NAME_MAX_LENGTH, message = CATEGORY_NAME_LENGTH)
    String name,
    @NotBlank(message = CATEGORY_DESCRIPTION_REQUIRED)
    @Size(min = CATEGORY_DESCRIPTION_MIN_LENGTH, max = CATEGORY_DESCRIPTION_MAX_LENGTH, message = CATEGORY_DESCRIPTION_LENGTH)
    String description
) {
}
