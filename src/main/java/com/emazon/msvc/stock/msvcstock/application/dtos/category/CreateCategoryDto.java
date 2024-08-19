package com.emazon.msvc.stock.msvcstock.application.dtos.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateCategoryDto(
        @NotBlank(message = "Category name is mandatory")
        @Size(min = 3, max = 50, message = "Category name must be between 3 and 50 characters")
        String name,
        @NotBlank(message = "Category description is mandatory")
        @Size(min = 3, max = 90, message = "Category description must be between 3 and 255 characters")
        String description
) {
}
