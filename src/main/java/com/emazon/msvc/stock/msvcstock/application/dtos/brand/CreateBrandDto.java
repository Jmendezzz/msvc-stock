package com.emazon.msvc.stock.msvcstock.application.dtos.brand;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateBrandDto(
        @NotBlank(message = "Brand name is required")
        @Size(min = 3, max = 50, message = "Brand name must be between 3 and 50 characters")
        String name,
        @NotBlank(message = "Brand description is required")
        @Size(min = 3, max = 120, message = "Brand description must be between 3 and 120 characters")
        String description
) {
}
