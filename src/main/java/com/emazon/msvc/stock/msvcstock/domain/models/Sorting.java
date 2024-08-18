package com.emazon.msvc.stock.msvcstock.domain.models;

import com.emazon.msvc.stock.msvcstock.domain.enums.SortDirection;
import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
public class Sorting {
    private String field;
    private SortDirection direction;
}
