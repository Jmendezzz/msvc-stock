package com.emazon.msvc.stock.msvcstock.application.dtos.brand;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import static com.emazon.msvc.stock.msvcstock.domain.utils.constants.brand.BrandValidationConstant.*;
import static com.emazon.msvc.stock.msvcstock.domain.utils.constants.brand.BrandValidationMessage.*;


public record CreateBrandRequestDto(
        @NotBlank(message = BRAND_NAME_REQUIRED)
        @Size(min = BRAND_NAME_MIN_LENGTH, max = BRAND_NAME_MAX_LENGTH, message = BRAND_NAME_LENGTH)
        String name,
        @NotBlank(message = BRAND_DESCRIPTION_REQUIRED)
        @Size(min = BRAND_DESCRIPTION_MIN_LENGTH, max = BRAND_DESCRIPTION_MAX_LENGTH, message = BRAND_DESCRIPTION_LENGTH)
        String description
) {
}
