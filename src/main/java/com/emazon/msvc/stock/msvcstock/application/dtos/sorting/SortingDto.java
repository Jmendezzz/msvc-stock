package com.emazon.msvc.stock.msvcstock.application.dtos.sorting;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record SortingDto (
        @NotBlank(message = "Sort field must not be blank")
        String sortBy,
        @Pattern(regexp = "ASC|DESC", message = "Direction must be either ASC or DESC")
       String direction
){
}
