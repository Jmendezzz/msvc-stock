package com.emazon.msvc.stock.msvcstock.application.dtos.pagination;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record PaginationDto(
        @NotNull(message = "Page number is required")
        @Min(value = 0, message = "Page number must be greater than or equal to 0")
        int page,
        @NotNull(message = "Page size is required")
        @Min(value = 1, message = "Page size must be greater than or equal to 1")
        int size
) {
}
